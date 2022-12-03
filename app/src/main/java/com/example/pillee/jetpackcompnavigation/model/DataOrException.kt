package com.example.pillee.jetpackcompnavigation.model

data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)