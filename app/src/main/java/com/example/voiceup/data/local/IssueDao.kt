package com.example.voiceup.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueDao {
    @Query("SELECT * FROM info WHERE userId = :userId ORDER BY id DESC")
    fun getAllIssues(userId: String): Flow<List<IssueInfo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(info: IssueInfo)

    @Delete
    suspend fun delete(info: IssueInfo)

    @Update
    suspend fun update(info: IssueInfo)
}