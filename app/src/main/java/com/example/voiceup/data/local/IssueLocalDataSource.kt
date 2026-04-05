package com.example.voiceup.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IssueLocalDataSource @Inject constructor(
    private val dao: IssueDao
) {
    fun getIssues(userId: String): Flow<List<IssueInfo>> {
        return dao.getAllIssues(userId)
    }

    suspend fun clearAndInsert(userId: String, issues: List<IssueInfo>) {
        dao.deleteByUser(userId)
        dao.insertAll(issues)
    }
}