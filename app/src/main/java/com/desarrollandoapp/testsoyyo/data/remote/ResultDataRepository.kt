package com.desarrollandoapp.testsoyyo.data.remote

data class ResultDataRepository<out T>(val status: Status, val data: T?) {

    enum class Status {
        SUCCESS,
        ERROR,
    }

    companion object {
        fun <T> success(data: T): ResultDataRepository<T> {
            return ResultDataRepository(Status.SUCCESS, data)
        }

        fun <T> error(): ResultDataRepository<T> {
            return ResultDataRepository(Status.ERROR, null)
        }
    }
}