package com.example.domain.ports.inbound

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.outbound.FoodPersistencePort
import com.example.domain.services.FoodService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class FoodServicePortTests(
        @MockK val foodPersistencePort: FoodPersistencePort
) {

    @InjectMockKs
    var foodServicePort = FoodService(foodPersistencePort)

    @Test
    fun `should create a new food and return it as a FoodDto`() {
        val createFoodDto = CreateFoodDto("Pizza", 1000)
        val fixture = FoodDto(UUID.randomUUID(), createFoodDto.name, createFoodDto.calories)
        every { foodPersistencePort.createFood(createFoodDto) } returns fixture

        val result = foodServicePort.createFood(createFoodDto)

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(fixture)

        verify(exactly = 1) { foodPersistencePort.createFood(createFoodDto) }
    }

    @Test
    fun `should get all registered foods mapped to FoodDto`() {
        val foodDtoFixture = listOf(
                FoodDto(id = UUID.randomUUID(), name = "Pizza", calories = 1000),
                FoodDto(id = UUID.randomUUID(), name = "Sushi", calories = 200)
        )
        every { foodPersistencePort.getFoods() } returns foodDtoFixture

        val result = foodServicePort.getFoods()

        assertEquals(foodDtoFixture, result)

        verify(exactly = 1) { foodPersistencePort.getFoods() }
    }

    @Test
    fun `should get a food by uuid mapped to FoodDto`() {
        val foodDtoFixture = FoodDto(id = UUID.randomUUID(), name = "Sushi", calories = 200)
        every { foodPersistencePort.getFood(foodDtoFixture.id.toString()) } returns foodDtoFixture

        val result = foodServicePort.getFood(foodDtoFixture.id.toString())

        assertEquals(foodDtoFixture, result)
    }

}