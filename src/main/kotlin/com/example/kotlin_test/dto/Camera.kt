package com.example.kotlin_test.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Camera(
    var id: Int = 0,
    var name: String? = null,
    @field:JsonProperty("rover_id")
    var roverId: Int = 0,
    @field:JsonProperty("full_name")
    var fullName: String? = null
)