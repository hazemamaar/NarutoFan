package com.example.home.domain

import com.example.core.response.BaseResponse
import com.example.core.response.ErrorResponse
import com.example.core.response.NetworkResponse
import com.example.core.response.Resource
import com.example.core.base.usecase.BaseUseCase
import com.example.home.data.remote.Hero
import com.example.home.data.repo.DataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNarutoUseCase @Inject constructor(val dataRepo: DataRepo): BaseUseCase<BaseResponse<List<Hero>>, List<Hero>, Any>() {

    override fun mapper(req: BaseResponse<List<Hero>>): List<Hero> {
        return req.data!!
    }

    override fun executeRemote(params: Any?): Flow<Resource<BaseResponse<List<Hero>>>> = flow{
        emit(dataRepo.getAllNaruto())
    }
}