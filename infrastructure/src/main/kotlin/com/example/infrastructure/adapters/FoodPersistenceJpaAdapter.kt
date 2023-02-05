package com.example.infrastructure.adapters

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.outbound.FoodPersistencePort
import com.example.infrastructure.entities.Food
import com.example.infrastructure.entities.toFoodDto
import com.example.infrastructure.repositories.FoodJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class FoodPersistenceJpaAdapter(
        @Autowired private val foodJpaRepository: FoodJpaRepository
) : FoodPersistencePort {

    override fun createFood(createFoodDto: CreateFoodDto): FoodDto {
        val food = Food.fromCreateFoodDto(createFoodDto)
        return foodJpaRepository.save(food).toFoodDto()
    }

    override fun getFoods() =
            foodJpaRepository.findAll().map { it.toFoodDto() }

    override fun getFood(uuid: String) =
            foodJpaRepository.findByIdOrNull(UUID.fromString(uuid))?.toFoodDto()
}