package com.carvalho.thumbinator.core.arch.state

import com.carvalho.thumbinator.core.arch.failure.BaseFailure

sealed class BaseState<out FailureType, out DataType> private constructor() {

    abstract val isSuccess: Boolean
    abstract val isFailure: Boolean
    abstract val isLoading: Boolean
    abstract val isEmpty: Boolean

    data class Success<T> internal constructor(val data: T) : BaseState<Nothing, T>() {
        override val isSuccess: Boolean
            get() = true
        override val isFailure: Boolean
            get() = false

        companion object {
            operator fun <T> invoke(data: T): BaseState<Nothing, T> = Success(data)
        }

        override val isLoading: Boolean
            get() = false
        override val isEmpty: Boolean
            get() = false
    }

    data class Failure<E> internal constructor(val message: String, val error: E) :
        BaseState<E, Nothing>() {
        override val isSuccess: Boolean
            get() = false
        override val isFailure: Boolean
            get() = true

        companion object {
            operator fun <E> invoke(message: String, failure: E): BaseState<E, Nothing> =
                Failure(message, failure)
        }

        override val isLoading: Boolean
            get() = false
        override val isEmpty: Boolean
            get() = false
    }

    data class Loading<E, T> internal constructor(val data: T? = null) : BaseState<E, T>() {
        override val isSuccess: Boolean
            get() = false
        override val isFailure: Boolean
            get() = false
        override val isLoading: Boolean
            get() = true
        override val isEmpty: Boolean
            get() = false

        companion object {
            operator fun <E, T> invoke(): BaseState<E, T> = Loading()
        }
    }

    class Empty<E, T> internal constructor() : BaseState<E, T>() {
        override val isSuccess: Boolean
            get() = false
        override val isFailure: Boolean
            get() = false
        override val isLoading: Boolean
            get() = false
        override val isEmpty: Boolean
            get() = true

        companion object {
            operator fun <E, T> invoke(): BaseState<E, T> = Empty()
        }
    }

    fun <Result> resolve(
        onSuccess: (success: DataType) -> Result,
        onFailure: (failure: BaseFailure<FailureType>) -> Result,
        onLoading: (loadingData: DataType?) -> Result,
        onEmpty: () -> Result,
    ): Result = when (this) {
        is Success -> onSuccess(data)
        is Empty -> onEmpty()
        is Failure -> onFailure(BaseFailure(message, error))
        is Loading -> onLoading(data)
    }
}