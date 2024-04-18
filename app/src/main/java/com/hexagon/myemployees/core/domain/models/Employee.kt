package com.hexagon.myemployees.core.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val birthDate: String,
    val cpf: String,
    val city: String,
    val photographURI: String,
    val isActive: Boolean,
)