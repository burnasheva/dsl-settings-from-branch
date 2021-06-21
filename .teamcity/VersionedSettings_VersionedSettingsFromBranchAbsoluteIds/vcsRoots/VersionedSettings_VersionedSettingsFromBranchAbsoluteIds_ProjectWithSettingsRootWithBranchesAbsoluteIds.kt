package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_ProjectWithSettingsRootWithBranchesAbsoluteIds : GitVcsRoot({
    uuid = "afa03682-233e-44e1-b62c-63b9236a7424"
    name = "project with settings root (with branches, absolute ids)"
    url = "https://github.com/burnasheva/dsl-settings-from-branch.git"
    branch = "refs/heads/absolute-ids"
    branchSpec = "+:refs/heads/*"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "burnasheva"
        password = "credentialsJSON:f582ecb0-3de3-44c1-bf46-161576de70be"
    }
})
