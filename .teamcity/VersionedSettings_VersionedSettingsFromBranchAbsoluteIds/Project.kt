package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds

import VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes.*
import VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.*
import VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "b55e75a5-4f9a-4f34-bd8e-f5b5cd032be2"
    id("VersionedSettings_VersionedSettingsFromBranchAbsoluteIds")
    parentId("VersionedSettings")
    name = "Versioned Settings from Branch (absolute ids)"

    vcsRoot(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_MstestProject)
    vcsRoot(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds)

    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_PublishPomXml)
    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleNetBuild)
    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ComposeBuildConfiguration)
    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleLsInWorkingDirectory)
    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ArtifactAndSnapshotDependency)
    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_RunMstests)
    buildType(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_OnlyArtifactsDependency_)

    template(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleTemplateWithRequirement)
    template(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleTemplateWithRequirementCopy)

    params {
        param("agent.host.requirement", "munit-367.labs.intellij.net")
        param("teamcity.internal.kotlinDsl.newProjects.allowUsingNonPortableDSL", "true")
    }

    features {
        versionedSettings {
            id = "PROJECT_EXT_237"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = "${VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds.id}"
            showChanges = false
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
