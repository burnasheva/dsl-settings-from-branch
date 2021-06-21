package VersionedSettings_VersionedSettingsFromBranchAbsoluteIds.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object VersionedSettings_VersionedSettingsFromBranchAbsoluteIds_MstestProject : GitVcsRoot({
    uuid = "46117c78-79b6-43e8-bccb-eafda32efe24"
    name = "mstest project"
    url = "https://github.com/burnasheva/mstest_dotnet3.git"
    branchSpec = "+:refs/heads/*"
    param("useAlternates", "true")
})
