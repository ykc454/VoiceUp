package com.example.voiceup.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class IssueInfo(
    @PrimaryKey
    val id: String = "",
    val prn: String,
    val name: String,
    val issue: String,
    val subject: String,
    val userId: String
)