package com.example.infrastructure

import com.example.domain.data.CreateFoodDto
import com.example.infrastructure.adapters.FoodPersistenceJpaAdapter
import com.example.infrastructure.entities.Food
import com.example.infrastructure.entities.toFoodDto
import com.example.infrastructure.repositories.FoodJpaRepository
import io.github.serpro69.kfaker.Faker
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@ExtendWith(MockKExtension::class)
@DisplayName("[FoodPersistenceAdapter Tests]")
class FoodPersistenceAdapterTests(
        @MockK val foodJpaRepository: FoodJpaRepository
) {

    private val faker: Faker = Faker()

    @InjectMockKs
    var foodPersistenceJpaAdapter = FoodPersistenceJpaAdapter(foodJpaRepository)

    @Test
    fun `should create food and map it to FoodDto`() {
        val createFoodDto = faker.randomProvider.randomClassInstance<CreateFoodDto>()
        val foodFixture = Food.fromCreateFoodDto(createFoodDto)
        val foodDtoFixture = foodFixture.toFoodDto()

        every { foodJpaRepository.save(any()) } returns foodFixture

        val result = foodPersistenceJpaAdapter.createFood(createFoodDto)

        assertEquals(foodDtoFixture, result)

        verify(exactly = 1) { foodJpaRepository.save(any()) }
    }

    @Test
    fun `should get all foods mapped to a FoodDto list`() {
        val foodsFixture = List(2) { faker.randomProvider.randomClassInstance<Food>() }

        every { foodJpaRepository.findAll() } returns foodsFixture

        val result = foodPersistenceJpaAdapter.getFoods()

        assertEquals(2, result.size)
    }

    @Test
    fun `should get a food by the uuid mapped to FoodDto`() {
        val foodFixture = faker.randomProvider.randomClassInstance<Food>()

        every { foodJpaRepository.findByIdOrNull(foodFixture.id) } returns foodFixture

        val result = foodPersistenceJpaAdapter.getFood(foodFixture.id.toString())

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(foodFixture)
    }

    @Test
    fun `should return null when getting a food by uuid`() {
        every { foodJpaRepository.findByIdOrNull(any()) } returns null

        val result = foodPersistenceJpaAdapter.getFood(UUID.randomUUID().toString())

        assertNull(result)
    }

}