package com.saqib.casestudy.model

data class PixaBayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
