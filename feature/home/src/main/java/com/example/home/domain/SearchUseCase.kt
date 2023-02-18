package com.example.home.domain

import com.example.core.base.usecase.BaseLocalUseCase
import com.example.core.base.usecase.BaseUseCase
import com.example.core.response.BaseResponse
import com.example.home.data.Hero
import com.example.home.data.repo.DataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val dataRepo: DataRepo) :BaseLocalUseCase<BaseResponse<List<Hero>>, List<Hero>, String>() {

    var shouldFetchNow = false
    override fun mapper(req: BaseResponse<List<Hero>>): List<Hero> {
        return req.data!!
    }

    override fun executeRemote(params: String?): Flow<BaseResponse<List<Hero>>> =flow{
        emit(dataRepo.searchHeroes(params!!))
    }

    override fun executeLocal(params: String?): Flow<List<Hero>> = dataRepo.searchLocalByName(params!!)


    override fun shouldFetch(localData: List<Hero>?): Boolean =shouldFetchNow

    override fun shouldCacheResponse(
        resData: BaseResponse<List<Hero>>,
        localData: List<Hero>,
    ): Boolean =false

    override suspend fun saveToLocal(res: List<Hero>) {
        TODO("Not yet implemented")
    }

}