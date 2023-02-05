package com.example.domain.services

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.inbound.FoodServicePort
import com.example.domain.ports.outbound.FoodPersistencePort

class FoodService(
        private val foodPersistencePort: FoodPersistencePort
) : FoodServicePort {

    override fun createFood(createFoodDto: CreateFoodDto) =
            foodPersistencePort.createFood(createFoodDto)

    override fun getFoods(): List<FoodDto> =
            foodPersistencePort.getFoods()

    override fun getFood(uuid: String): FoodDto? =
            foodPersistencePort.getFood(uuid)
}