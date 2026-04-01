package com.example.voiceup.data.local

import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.IssueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IssueDataRepository @Inject constructor(private val dao: IssueDao): IssueRepository {
    override fun getIssue(): Flow<List<Issue>> {
        return dao.getAllIssues().map { list ->
            list.map{info->
                Issue(
                    id = info.id,
                    name = info.name,
                    prn = info.prn,
                    issue = info.issue,
                    subject = info.subject
                )
            }
        }
    }

    override suspend fun addIssue(issue: Issue) {
         dao.insert(IssueInfo(name = issue.name,prn = issue.prn, issue = issue.issue, subject = issue.subject))
    }

    override suspend fun updateIssue(issue: Issue) {
        dao.update(IssueInfo(id = issue.id,name = issue.name,prn = issue.prn, issue = issue.issue, subject = issue.subject))
    }

    override suspend fun deleteIssue(issue: Issue) {
        dao.delete(IssueInfo(id = issue.id,name = issue.name,prn = issue.prn, issue = issue.issue, subject = issue.subject))
    }
}