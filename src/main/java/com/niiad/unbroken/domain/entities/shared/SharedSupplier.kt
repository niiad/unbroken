package com.niiad.unbroken.domain.entities.shared

data class SharedSupplier (
    private val id: Long,
    private val name: String,
    private val city: String,
    private val region: String,
    private val country: String,
    private val sellingPrice: String,
    private val delivery: Boolean,
    private val contact: String
)