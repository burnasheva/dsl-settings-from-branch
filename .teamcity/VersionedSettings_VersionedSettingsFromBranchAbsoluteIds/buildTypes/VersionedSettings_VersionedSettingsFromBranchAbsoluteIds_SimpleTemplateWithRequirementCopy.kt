package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleTemplateWithRequirementCopy : Template({
    uuid = "f80e6e3c-167a-4978-b56d-d7c05feabd4f"
    name = "Simple Template with requirement (branch)"

    params {
        param("agent.host.requirement", "nburn-serv2019-empty")
    }
})
