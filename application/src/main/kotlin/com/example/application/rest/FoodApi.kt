package com.example.application.rest

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.inbound.FoodServicePort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/food")
class FoodApi(
        private val foodServicePort: FoodServicePort
) {

    @PostMapping(produces = ["media-type/APPLICATION_JSON"])
    fun postFood(@RequestBody food: CreateFoodDto): ResponseEntity<FoodDto> {
        val persistedFoodDto = foodServicePort.createFood(food)
        return ResponseEntity.created(URI.create("/food/${persistedFoodDto.id}")).body(persistedFoodDto)
    }

}