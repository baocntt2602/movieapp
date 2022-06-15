package com.example.movieapplication.data.models

sealed class ApiResponse<out Result> {

    data class Success<Result>(val data: Result) : ApiResponse<Result>()

    data class ApiFailure(val message: String) : ApiResponse<Nothing>()
}