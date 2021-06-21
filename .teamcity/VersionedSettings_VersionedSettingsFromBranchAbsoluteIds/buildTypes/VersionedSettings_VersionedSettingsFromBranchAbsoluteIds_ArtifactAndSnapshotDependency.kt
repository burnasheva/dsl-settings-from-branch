package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ArtifactAndSnapshotDependency : BuildType({
    uuid = "e547d087-dd04-4468-bc7d-fd1b80b14c69"
    name = "artifact and snapshot dependency"

    vcs {
        root(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds)
    }

    steps {
        maven {
            goals = "clean compile"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
    }

    dependencies {
        dependency(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_PublishPomXml) {
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
