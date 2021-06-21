package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.buildTypes

import VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.vcsLabeling

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_PublishPomXml : BuildType({
    templates(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_SimpleTemplateWithRequirement)
    uuid = "77fa6bd8-fc31-4a85-a7a2-cded9279fb46"
    name = "publish pom.xml"
    id("VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_PublishPomXml_2")

    artifactRules = "pom.xml"

    vcs {
        root(VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots.VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds, "+:pom.xml", "+:many-small-files => small-files-directory")
    }

    features {
        vcsLabeling {
            id = "BUILD_EXT_1"
            vcsRootId = "${VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds.id}"
            successfulOnly = true
        }
        pullRequests {
            id = "BUILD_EXT_2"
            vcsRootExtId = "${VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds.id}"
            provider = github {
                authType = vcsRoot()
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})
