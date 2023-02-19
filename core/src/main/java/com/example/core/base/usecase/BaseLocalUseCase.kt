package com.example.core.base.usecase


import com.example.core.response.BaseCommonResponse
import com.example.core.response.NetworkResponse
import com.example.core.response.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


//Use this class if you want to manage the cycle between remote and local APIs
abstract class BaseLocalUseCase<RequestType : BaseCommonResponse, ResultType : Any, in Params> :
    BaseCommonUseCase<RequestType, ResultType, Params>() {
    // run the local fun
    abstract fun executeLocal(params: Params?): Flow<ResultType>

    // ask if should we run the network or just pass the local data ?
    abstract fun shouldFetch(localData: ResultType?): Boolean

    // ask if we should cache the response in local DB
    abstract fun shouldCacheResponse(resData: RequestType, localData: ResultType): Boolean

    // action save to local DB
    abstract suspend fun saveToLocal(res: ResultType)

    fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (Resource<ResultType>) -> Unit = {}
    ) {
        scope.launch(handler(onResult) + Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            //Run local first
            runFlow(executeLocal(params), onResult).collect { localData ->
                if (shouldFetch(localData)) { //call network and get result
                    runFlow(executeRemote(params), onResult).collect {
                        try {
                            if (it.success == true
                            ) {
                                val res = mapper(it)
                                val shouldCache = shouldCacheResponse(it, localData)
                                if (shouldCache)
                                    saveToLocal(res)
                                onResult.invoke(Resource.success(res))
                            } else {
                                showFailureMessage(onResult, it.message)
                            }
                        }catch (e: Exception) {
                            showFailureMessage(
                                onResult,
                                it.message
                            )
                        }
                    }
                } else //get local
                    onResult.invoke(Resource.success(localData))
                onResult.invoke(Resource.loading(false))
            }
        }
    }

}