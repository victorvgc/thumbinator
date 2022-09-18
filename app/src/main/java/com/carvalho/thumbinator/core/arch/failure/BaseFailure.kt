package com.carvalho.thumbinator.core.arch.failure

open class BaseFailure< out FailureData>(val message: String, val error: FailureData?) {
    override fun toString(): String {
        return message
    }
}