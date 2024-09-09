package com.example.dyma_chap8.network.response

data class ApiResponse<T>(val data: T?, val error: String?)
