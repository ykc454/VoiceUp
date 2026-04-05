package com.example.voiceup.data

import com.example.voiceup.data.local.IssueInfo
import com.example.voiceup.data.local.IssueLocalDataSource
import com.example.voiceup.data.remote.IssueRemoteDataSource
import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.repo.IssueRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val local: IssueLocalDataSource,
    private val remote: IssueRemoteDataSource
) : IssueRepository {

    override fun getIssue(userId: String): Flow<List<Issue>> {

        // 🔥 Sync remote → local
        CoroutineScope(Dispatchers.IO).launch {
            remote.getIssues(userId).collect { issues ->
                local.clearAndInsert(
                    userId,
                    issues.map {
                        IssueInfo(
                            id = it.id.toIntOrNull() ?: 0,
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

        // ✅ UI reads ONLY from local
        return local.getIssues(userId).map { list ->
            list.map {
                Issue(
                    id = it.id.toString(),
                    name = it.name,
                    prn = it.prn,
                    issue = it.issue,
                    subject = it.subject,
                    userId = it.userId
                )
            }
        }
    }

    override suspend fun addIssue(issue: Issue) {
        remote.addIssue(issue)
    }

    override suspend fun updateIssue(issue: Issue) {
        remote.updateIssue(issue)
    }

    override suspend fun deleteIssue(issue: Issue) {
        remote.deleteIssue(issue)
    }
}