package com.arnoract.piggiplan.core.api.branch

import com.arnoract.piggiplan.core.api.branch.model.BranchResponse
import retrofit2.Response
import retrofit2.http.GET

interface BranchApi {
    @GET("branches.txt?alt=media&token=43519cf0-eee9-4bce-8d1d-9f8ca76a05a9")
    suspend fun fetchAllBranches(
    ): Response<List<BranchResponse>>
}