package com.example.domain.ports.inbound

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.outbound.FoodPersistencePort
import com.example.domain.services.FoodService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.*
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
    }

}