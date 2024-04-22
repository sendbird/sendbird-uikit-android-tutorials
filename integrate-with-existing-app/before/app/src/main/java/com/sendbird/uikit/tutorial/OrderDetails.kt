package com.sendbird.uikit.tutorial

data class OrderDetails(
    val orderNumber: String,
    val orderStatus: String,
    val orderItems: List<String>,
    val deliveryPersonId: String
)
