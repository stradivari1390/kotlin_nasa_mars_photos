package com.example.kotlin_test.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MarsPhotosResponse(
    @JsonProperty("photos")
    var photos: List<Photo>? = null
)