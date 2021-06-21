package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.retryBuild

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ComposeBuildConfiguration : BuildType({
    uuid = "64aa6d8b-5052-46a2-93c6-900a3690df3e"
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
        snapshot(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ArtifactAndSnapshotDependency) {
        }
        snapshot(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_OnlyArtifactsDependency_) {
        }
        snapshot(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_RunMstests) {
        }
        artifacts(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_PublishPomXml) {
            cleanDestination = true
            artifactRules = "pom.xml"
        }
    }
})
