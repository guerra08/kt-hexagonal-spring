package com.example.infrastructure.entities

import com.example.domain.data.CreateFoodDto
import com.example.domain.data.FoodDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "FOODS")
class Food(
        @Id var id: UUID? = null,
        @Column(name = "NAME") val name: String,
        @Column(name = "CALORIES") val calories: Int
) {
    companion object {
        fun fromCreateFoodDto(createFoodDto: CreateFoodDto) =
                Food(name = createFoodDto.name, calories = createFoodDto.calories)
    }
}

fun Food.toFoodDto() = FoodDto(id = this.id, name = this.name, calories = this.calories)