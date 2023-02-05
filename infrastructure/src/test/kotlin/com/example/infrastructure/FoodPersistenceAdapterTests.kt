package com.example.infrastructure

import com.example.domain.data.CreateFoodDto
import com.example.infrastructure.adapters.FoodPersistenceJpaAdapter
import com.example.infrastructure.entities.Food
import com.example.infrastructure.entities.toFoodDto
import com.example.infrastructure.repositories.FoodJpaRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class FoodPersistenceAdapterTests(
        @MockK val foodJpaRepository: FoodJpaRepository
) {

    @InjectMockKs
    var foodPersistenceJpaAdapter = FoodPersistenceJpaAdapter(foodJpaRepository)

    @Test
    fun `should create food and map it to FoodDto`() {
        val createFoodDto = CreateFoodDto(name = "Pizza", calories = 1000)
        val foodFixture = Food.fromCreateFoodDto(createFoodDto)
        val foodDtoFixture = foodFixture.toFoodDto()

        every { foodJpaRepository.save(foodFixture) } returns foodFixture

        TODO("Fix this test")

        val result = foodPersistenceJpaAdapter.createFood(createFoodDto)

        assertEquals(foodDtoFixture, result)
    }

}