package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_OnlyArtifactsDependency : BuildType({
    uuid = "afc82687-3659-4db3-bc23-1a21560ea7a5"
    name = "only artifacts dependency"

    vcs {
        root(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds)
    }

    dependencies {
        artifacts(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_PublishPomXml) {
            buildRule = lastFinished()
            cleanDestination = true
            artifactRules = "pom.xml => artifact"
        }
    }
})
