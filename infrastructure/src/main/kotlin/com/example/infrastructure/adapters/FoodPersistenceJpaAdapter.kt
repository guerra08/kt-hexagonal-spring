package com.example.infrastructure.adapters

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.outbound.FoodPersistencePort
import com.example.infrastructure.entities.Food
import com.example.infrastructure.entities.toFoodDto
import com.example.infrastructure.repositories.FoodJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FoodPersistenceJpaAdapter(
        @Autowired private val foodJpaRepository: FoodJpaRepository
) : FoodPersistencePort {

    override fun createFood(createFoodDto: CreateFoodDto): FoodDto {
        var food = Food.fromCreateFoodDto(createFoodDto)
        food = foodJpaRepository.save(food)
        return food.toFoodDto()
    }
}