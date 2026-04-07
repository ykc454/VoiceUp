package com.example.voiceup.domain

import com.example.voiceup.data.local.IssueInfo

import com.example.voiceup.domain.Issue

data class Issue(
    var id: String = "",
    var prn: String = "",
    var name: String = "",
    var issue: String = "",
    var subject: String = "",
    var userId: String = ""
)


// This "adds" the toInfo() function to the Issue class
fun Issue.toInfo(): IssueInfo {
    return IssueInfo(
        id = this.id,
        name = this.name,
        prn = this.prn,
        issue = this.issue,
        subject = this.subject,
        userId = this.userId
    )
}

//this to convert from Database back to UI
fun IssueInfo.toDomain(): Issue {
    return Issue(
        id = this.id,
        name = this.name,
        prn = this.prn,
        issue = this.issue,
        subject = this.subject,
        userId = this.userId
    )
}