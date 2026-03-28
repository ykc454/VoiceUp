package com.example.voiceup.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IssueInfo::class], version = 1, exportSchema = false)
abstract class IssueDatabase: RoomDatabase() {
    abstract fun issueDao(): IssueDao
}