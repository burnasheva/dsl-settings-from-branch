package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleLsInWorkingDirectory : BuildType({
    uuid = "ec2a7b01-b5ce-4e53-b543-3c44aa28d5e4"
    name = "simple ls in working directory"

    vcs {
        root(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds)
    }

    steps {
        script {
            scriptContent = "ls -a"
        }
    }
})
