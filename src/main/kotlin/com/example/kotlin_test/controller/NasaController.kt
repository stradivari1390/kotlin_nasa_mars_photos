package com.example.kotlin_test.controller

import com.example.kotlin_test.service.NasaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MarsRoverController(private val nasaService: NasaService) {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/mars-photos")
    fun getMarsPhotos(
        @RequestParam(required = false) sol: String?,
        @RequestParam(required = false) earthDate: String?,
        @RequestParam(defaultValue = "1") page: Int,
        model: Model
    ): String {
        if (sol.isNullOrBlank() && earthDate.isNullOrEmpty()) {
            model.addAttribute("error", "Either 'sol' or 'earthDate' must be provided")
            return "error"
        }

        val solAsInt = sol?.toIntOrNull()
        if (sol != null && solAsInt == null) {
            model.addAttribute("error", "Invalid 'sol' value provided")
            return "error"
        }

        val response = when {
            solAsInt != null -> nasaService.getMarsPhotosBySol(solAsInt, page)
            !earthDate.isNullOrEmpty() -> nasaService.getMarsPhotosByEarthDate(earthDate, page)
            else -> throw IllegalArgumentException("This should never happen because of the check above")
        }
        model.addAttribute("photos", response.photos)
        model.addAttribute("currentPage", page)
        model.addAttribute("sol", solAsInt)
        model.addAttribute("earthDate", earthDate)
        return "marsPhotos"
    }
}