package com.example.domain.ports.inbound

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto

interface FoodServicePort {

    fun createFood(createFoodDto: CreateFoodDto): FoodDto

    fun getFoods(): List<FoodDto>

    fun getFood(uuid: String): FoodDto?

}