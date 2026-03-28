package com.example.voiceup.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class IssueInfo(
    @PrimaryKey(true)
    val id: Int = 0,
    val prn: String,
    val name: String,
    val issue: String
)
