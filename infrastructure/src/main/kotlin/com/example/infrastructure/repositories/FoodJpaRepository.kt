package com.example.infrastructure.repositories

import com.example.infrastructure.entities.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FoodJpaRepository : JpaRepository<Food, UUID> {

    fun getAllByName(name: String)

}