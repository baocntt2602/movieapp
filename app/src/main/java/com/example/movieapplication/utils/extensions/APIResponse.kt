package com.example.movieapplication.utils.extensions

import com.example.movieapplication.data.models.ApiResponse

@Throws(Throwable::class)
suspend fun <T : Any> ApiResponse<T>.coSuccessOrThrow(
    func: suspend (T) -> Unit
) {
    when (this) {
        is ApiResponse.Success -> func.invoke(this.data)
        else -> throw Throwable("Not Success response")
    }
}