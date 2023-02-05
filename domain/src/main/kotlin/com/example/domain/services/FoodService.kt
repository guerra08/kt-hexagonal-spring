package com.example.domain.services

import com.example.domain.data.CreateFoodDto
import com.example.domain.ports.inbound.FoodServicePort
import com.example.domain.ports.outbound.FoodPersistencePort

class FoodService(
        private val foodPersistencePort: FoodPersistencePort
) : FoodServicePort {

    override fun createFood(createFoodDto: CreateFoodDto) =
            foodPersistencePort.createFood(createFoodDto)
}