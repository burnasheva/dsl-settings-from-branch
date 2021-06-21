package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetTest

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleNetBuild : BuildType({
    uuid = "ebef93ef-45bf-40cb-afa3-8d8068640883"
    name = "Simple .NET Build"

    vcs {
        root(AbsoluteId("HttpsGithubComBurnashevaMstestDotnet3refsHeadsMaster_3"))
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
