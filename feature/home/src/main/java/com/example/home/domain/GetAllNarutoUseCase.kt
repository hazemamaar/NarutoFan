package com.example.home.domain

import android.util.Log
import com.example.core.base.usecase.BaseLocalUseCase
import com.example.core.response.BaseResponse
import com.example.core.base.usecase.BaseUseCase
import com.example.home.data.Hero
import com.example.home.data.repo.DataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNarutoUseCase @Inject constructor(private val dataRepo: DataRepo): BaseLocalUseCase<BaseResponse<List<Hero>>, List<Hero>, Any>() {
    var shouldFetchNow = false
    override fun mapper(req: BaseResponse<List<Hero>>): List<Hero> {
        return req.data!!
    }

    override fun executeRemote(params: Any?): Flow<BaseResponse<List<Hero>>> = flow{
        emit(dataRepo.getAllNaruto())
    }

    override fun executeLocal(params: Any?): Flow<List<Hero>> =flow {
        emit(dataRepo.getLocalHeroes())
    }

    override fun shouldFetch(localData: List<Hero>?): Boolean =shouldFetchNow

    override fun shouldCacheResponse(
        resData: BaseResponse<List<Hero>>,
        localData: List<Hero>,
    ): Boolean =true

    override suspend fun saveToLocal(res: List<Hero>) {
        dataRepo.insertHeroes(res)
    }
}