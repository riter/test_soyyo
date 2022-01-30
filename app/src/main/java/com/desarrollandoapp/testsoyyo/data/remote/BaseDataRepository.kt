package com.desarrollandoapp.testsoyyo.data.remote

import retrofit2.Response

abstract class BaseDataRepository {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultDataRepository<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return ResultDataRepository.success(body)
            }
            return ResultDataRepository.error()

        } catch (e: Exception) {
            e.printStackTrace()
            return ResultDataRepository.error()
        }
    }

}