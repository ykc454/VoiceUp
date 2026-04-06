package com.example.voiceup.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IssueLocalDataSource @Inject constructor(
    private val dao: IssueDao
) {
    fun getIssues(userId: String): Flow<List<IssueInfo>> {
        return dao.getAllIssues(userId)
    }

    suspend fun updateLocalCache(userId: String, issues: List<IssueInfo>) {
        //and prevents UI flickering
        dao.upsertIssues(userId, issues)
    }

    suspend fun insertSingle(info: IssueInfo) {
        dao.insert(info)
    }

    suspend fun updateSingle(info: IssueInfo) {
        dao.update(info)
    }

    suspend fun deleteSingle(info: IssueInfo) {
        dao.delete(info)
    }
}