package com.example.core.base.usecase


import com.example.core.response.BaseCommonResponse
import com.example.core.response.NetworkResponse
import com.example.core.response.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Created by Mahmoud Ayman on 1/4/2021.
 */
/**
- Acts as a contract for all the use cases in our application:
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.

 * @param RequestType: a return type which is the result of the network execution.
 * @param Params: a parameters class which will be consumed inside the [invoke] function
in case we need extra data for our use case.
 * @param ResultType: the result returned after mapping the response to to the View
 */
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
//                        when (it) {
//                            is Resource.Success -> {
//                                if (it.body.success == true) {
//                                    val res = mapper(it.body)
//                                    val shouldCache = shouldCacheResponse(it.body, localData)
//                                    if (shouldCache)
//                                        saveToLocal(res)
//                                    onResult.invoke(Resource.success(res))
//                                } else {
//                                    showFailureMessage(onResult, it.body.message)
//                                }
//                            }
//                            is NetworkResponse.NetworkError -> showFailureMessage(
//                                onResult,
//                                it.error.toString()
//                            )
//                            is NetworkResponse.ServerError -> showFailureMessage(
//                                onResult,
//                                it.body?.message
//                            )
//                            is NetworkResponse.UnknownError -> showFailureMessage(
//                                onResult,
//                                it.error.toString()
//                            )
//                        }
                    }
                } else //get local
                    onResult.invoke(Resource.success(localData))
                onResult.invoke(Resource.loading(false))
            }
        }
    }

}