package com.example.voiceup.data

import com.example.voiceup.data.local.IssueInfo
import com.example.voiceup.data.local.IssueLocalDataSource
import com.example.voiceup.data.remote.IssueRemoteDataSource
import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.repo.IssueRepository
import com.example.voiceup.domain.toDomain
import com.example.voiceup.domain.toInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val local: IssueLocalDataSource,
    private val remote: IssueRemoteDataSource
) : IssueRepository {

    override suspend fun syncIssues(userId: String) {
        remote.getIssues(userId).collectLatest { issues ->
            local.updateLocalCache(
                userId,
                issues.map {
                    IssueInfo(
                        id = it.id,
                        name = it.name,
                        prn = it.prn,
                        issue = it.issue,
                        subject = it.subject,
                        userId = it.userId
                    )
                }
            )
        }
    }

    override fun getIssue(userId: String): Flow<List<Issue>> {

        //UI reads ONLY from local
        return local.getIssues(userId).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun addIssue(issue: Issue) {
        local.insertSingle(issue.toInfo())
        remote.addIssue(issue)
    }

    override suspend fun updateIssue(issue: Issue) {
        local.updateSingle(issue.toInfo())
        remote.updateIssue(issue)
    }

    override suspend fun deleteIssue(issue: Issue) {
        local.deleteSingle(issue.toInfo())
        remote.deleteIssue(issue)
    }
}