package com.example.pillee.jetpackcompnavigation.screens

import com.example.pillee.jetpackcompnavigation.model.Pills

data class PillDetailState(
    val isLoading: Boolean = false,
    val pill: Pills? = null,
    val error: String = ""

)
