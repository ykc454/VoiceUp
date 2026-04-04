package com.example.voiceup.data.remote

import com.example.voiceup.domain.Issue
import com.example.voiceup.domain.IssueRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseRepository : IssueRepository {

    private val db = FirebaseDatabase.getInstance().reference

    override fun getIssue(userId: String): Flow<List<Issue>> = callbackFlow {

        val userIssuesRef = db.child("issues").child(userId)

        val listener = userIssuesRef.addValueEventListener(object :
            com.google.firebase.database.ValueEventListener {

            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                val issues = snapshot.children.mapNotNull {
                    it.getValue(Issue::class.java)
                }
                trySend(issues)
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                close(error.toException())
            }
        })

        awaitClose { userIssuesRef.removeEventListener(listener) }
    }

    override suspend fun addIssue(issue: Issue) {
        val userIssuesRef = db.child("issues").child(issue.userId)
        val id = userIssuesRef.push().key ?: return
        userIssuesRef.child(id).setValue(issue.copy(id = id))
    }

    override suspend fun updateIssue(issue: Issue) {
        db.child("issues")
            .child(issue.userId)
            .child(issue.id)
            .setValue(issue)
    }

    override suspend fun deleteIssue(issue: Issue) {
        db.child("issues")
            .child(issue.userId)
            .child(issue.id)
            .removeValue()
    }
}