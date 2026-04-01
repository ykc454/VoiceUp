package com.example.voiceup.data.remote

import com.google.firebase.database.FirebaseDatabase

object FirebaseRepository {
    private val db = FirebaseDatabase.getInstance().getReference()

    fun addIssue (issueInfo: IssueInfo) {
        val id = db.push().key!!
        db.child(id).setValue(issueInfo.copy(id = id))
    }
}