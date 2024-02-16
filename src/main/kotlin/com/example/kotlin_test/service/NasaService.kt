package com.example.kotlin_test.service

import com.example.kotlin_test.dto.MarsPhotosResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Service
class NasaService(private val restTemplate: RestTemplate) {

    @Value("\${nasa.api.key}")
    private val apiKey: String? = null

    fun getMarsPhotosBySol(sol: Int, page: Int = 1): MarsPhotosResponse =
        fetchMarsPhotos("sol=$sol&page=$page")

    fun getMarsPhotosByEarthDate(earthDate: String, page: Int = 1): MarsPhotosResponse {
        require(earthDate.isNotEmpty()) {
            throw IllegalArgumentException("Earth date must not be empty")
        }
        return fetchMarsPhotos("earth_date=$earthDate&page=$page")
    }

    private fun fetchMarsPhotos(queryParam: String): MarsPhotosResponse {
        val url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?$queryParam&api_key=$apiKey"
        try {
            val response = restTemplate.getForObject(url, MarsPhotosResponse::class.java)
            return response ?: throw IllegalStateException("API response is null.")
        } catch (ex: HttpClientErrorException) {
            throw IllegalStateException("Failed to fetch Mars photos, API returned error status.", ex)
        } catch (ex: Exception) {
            throw IllegalStateException("Unknown error occurred while fetching Mars photos.", ex)
        }
    }
}