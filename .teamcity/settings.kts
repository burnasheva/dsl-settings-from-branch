import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetTest
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.retryBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.2"

project {

    vcsRoot(MstestProject)

    buildType(SimpleLsInWorkingDirectory)
    buildType(PublishPomXml)
    buildType(ComposeBuildConfiguration)
    buildType(OnlyArtifactsDependency)
    buildType(ArtifactAndSnapshotDependency)
    buildType(RunMstests)
}

object ArtifactAndSnapshotDependency : BuildType({
    name = "artifact and snapshot dependency"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            goals = "clean compile"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    dependencies {
        dependency(PublishPomXml) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                cleanDestination = true
                artifactRules = "pom.xml => artifacts"
            }
        }
    }
})

object ComposeBuildConfiguration : BuildType({
    name = "compose build configuration"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.COMPOSITE
    detectHangingBuilds = false

    vcs {
        showDependenciesChanges = true
    }

    triggers {
        retryBuild {
            delaySeconds = 2
            moveToTheQueueTop = true
        }
    }

    dependencies {
        snapshot(ArtifactAndSnapshotDependency) {
        }
        snapshot(OnlyArtifactsDependency) {
        }
        snapshot(RunMstests) {
        }
        artifacts(PublishPomXml) {
            cleanDestination = true
            artifactRules = "pom.xml"
        }
    }
})

object OnlyArtifactsDependency : BuildType({
    name = "only artifacts dependency"

    vcs {
        root(DslContext.settingsRoot)
    }

    dependencies {
        artifacts(PublishPomXml) {
            buildRule = lastFinished()
            cleanDestination = true
            artifactRules = "pom.xml => artifact_branch"
        }
    }
})

object PublishPomXml : BuildType({
    name = "publish pom.xml"

    artifactRules = "pom.xml => sub_directory"

    params {
        param("teamcity.internal.versionedSettings.reportInapplicable.vcsRoots", "FAILURE")
    }

    vcs {
        root(DslContext.settingsRoot, "+:pom.xml", "+:many-small-files => small-files-directory-2")
    }

    features {
        vcsLabeling {
            vcsRootId = "${DslContext.settingsRoot.id}"
            successfulOnly = true
        }
        pullRequests {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            provider = github {
                authType = vcsRoot()
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})

object RunMstests : BuildType({
    name = "run mstests"

    vcs {
        root(MstestProject)
    }

    steps {
        dotnetBuild {
            projects = "MSTestCore.sln"
        }
        dotnetTest {
            projects = "PrimeService.Tests/PrimeService.Tests.csproj"
        }
    }
})

object SimpleLsInWorkingDirectory : BuildType({
    name = "simple ls in working directory"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = "ls -a"
        }
    }
})

object MstestProject : GitVcsRoot({
    name = "mstest project"
    url = "https://github.com/burnasheva/mstest_dotnet3.git"
    branchSpec = "+:refs/heads/*"
})
