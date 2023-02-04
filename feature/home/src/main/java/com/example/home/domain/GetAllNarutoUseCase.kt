package com.example.home.domain

import com.example.core.response.BaseResponse
import com.example.core.response.ErrorResponse
import com.example.core.response.NetworkResponse
import com.example.core.usecase.BaseUseCase
import com.example.home.data.remote.APIResponse
import com.example.home.data.repo.DataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNarutoUseCase @Inject constructor(val dataRepo: DataRepo): BaseUseCase<BaseResponse<APIResponse>,APIResponse,Any>() {
    override fun mapper(req: BaseResponse<APIResponse>): APIResponse {
        return APIResponse(req.data!!.heroes)
    }

    override fun executeRemote(params: Any?): Flow<NetworkResponse<BaseResponse<APIResponse>, ErrorResponse>>  = flow{
        emit(dataRepo.getAllNaruto())
    }
}