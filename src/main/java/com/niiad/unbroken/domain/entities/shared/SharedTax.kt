package com.niiad.unbroken.domain.entities.shared

data class SharedTax (
    private val id: Long,
    private val name: String,
    private val rate: String,
    private val country: String
)
