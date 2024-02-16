package com.example.kotlin_test.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Photo(
    var id: Int,
    var sol: Int,
    var camera: Camera,
    @JsonProperty("img_src")
    var imgSrc: String,
    @JsonProperty("earth_date")
    var earthDate: Date,
    var rover: Rover
)