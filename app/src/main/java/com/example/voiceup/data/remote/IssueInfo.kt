package com.example.voiceup.data.remote

data class IssueFirebaseData(
    val id: String = "",
    val prn: String,
    val name: String,
    val issue: String,
    val subject: String,
    val userId: String
)
