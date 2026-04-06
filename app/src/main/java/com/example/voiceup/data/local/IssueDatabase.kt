package com.example.voiceup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IssueInfo::class], version = 4, exportSchema = false)
abstract class IssueDatabase: RoomDatabase() {
    abstract fun issueDao(): IssueDao
}