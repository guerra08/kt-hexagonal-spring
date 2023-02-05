package com.example.domain.ports.outbound

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto

interface FoodPersistencePort {

    fun createFood(createFoodDto: CreateFoodDto): FoodDto

}