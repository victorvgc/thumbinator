package com.carvalho.thumbinator.core.arch.data_helpers

class Response<out T>(val data: T? = null, val errorMsg: String? = null) {

    init {
        if (data == null && errorMsg == null) {
            throw IllegalArgumentException("At least one of constructors parameters must not be null!")
        }
    }

    override fun toString(): String {
        return data?.toString() ?: errorMsg ?: "Null response"
    }
}