package com.example.application.configuration

import com.example.domain.ports.inbound.FoodServicePort
import com.example.domain.ports.outbound.FoodPersistencePort
import com.example.domain.services.FoodService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansConfiguration {

    @Bean
    fun getFoodServicePort(foodPersistencePort: FoodPersistencePort): FoodServicePort =
            FoodService(foodPersistencePort)

}