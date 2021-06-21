package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetTest

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_RunMstests : BuildType({
    uuid = "1b3ecbf1-23a4-4964-acff-57ef06e0fc25"
    name = "run mstests"

    vcs {
        root(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_MstestProject)
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
