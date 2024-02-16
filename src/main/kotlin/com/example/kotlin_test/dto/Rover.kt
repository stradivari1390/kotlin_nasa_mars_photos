package com.example.kotlin_test.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Rover (
    var id: Int = 0,
    var name: String? = null,

    @field:JsonProperty("landing_date")
    var landingDate: Date? = null,

    @field:JsonProperty("launch_date")
    var launchDate: Date? = null,
    var status: String? = null
)