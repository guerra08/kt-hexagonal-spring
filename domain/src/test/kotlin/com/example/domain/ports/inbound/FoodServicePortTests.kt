package com.example.domain.ports.inbound

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import com.example.domain.ports.outbound.FoodPersistencePort
import com.example.domain.services.FoodService
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
import java.util.*
import kotlin.collections.List

@ExtendWith(MockKExtension::class)
@DisplayName("[FoodServicePort Tests]")
class FoodServicePortTests(
        @MockK val foodPersistencePort: FoodPersistencePort
) {

    private val faker: Faker = Faker()

    @InjectMockKs
    var foodServicePort = FoodService(foodPersistencePort)

    @Test
    fun `should create a new food and return it as a FoodDto`() {
        val createFoodDto = faker.randomProvider.randomClassInstance<CreateFoodDto>()
        val fixture = faker.randomProvider.randomClassInstance<FoodDto>()
        every { foodPersistencePort.createFood(createFoodDto) } returns fixture

        val result = foodServicePort.createFood(createFoodDto)

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(fixture)

        verify(exactly = 1) { foodPersistencePort.createFood(createFoodDto) }
    }

    @Test
    fun `should get all registered foods mapped to FoodDto`() {
        val foodDtoFixture = List(2) { faker.randomProvider.randomClassInstance<FoodDto>() }
        every { foodPersistencePort.getFoods() } returns foodDtoFixture

        val result = foodServicePort.getFoods()

        assertEquals(foodDtoFixture, result)

        verify(exactly = 1) { foodPersistencePort.getFoods() }
    }

    @Test
    fun `should get a food by uuid mapped to FoodDto`() {
        val foodDtoFixture = faker.randomProvider.randomClassInstance<FoodDto>()
        every { foodPersistencePort.getFood(foodDtoFixture.id.toString()) } returns foodDtoFixture

        val result = foodServicePort.getFood(foodDtoFixture.id.toString())

        assertEquals(foodDtoFixture, result)
    }

    @Test
    fun `should return null when getting by uuid for non existing food`() {
        every { foodPersistencePort.getFood(any()) } returns null

        val result = foodServicePort.getFood(UUID.randomUUID().toString())

        assertNull(result)
    }

}