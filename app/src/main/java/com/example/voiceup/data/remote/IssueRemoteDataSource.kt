package com.example.voiceup.data.remote

import com.example.voiceup.domain.Issue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class IssueRemoteDataSource @Inject constructor() {

    private val db = FirebaseDatabase.getInstance().reference

    fun getIssues(userId: String): Flow<List<Issue>> = callbackFlow {
        val ref = db.child("issues").child(userId)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val issues = snapshot.children.mapNotNull {
                    it.getValue(Issue::class.java)
                }
                trySend(issues)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        ref.addValueEventListener(listener)

        awaitClose {
            ref.removeEventListener(listener)
        }
    }

     fun addIssue(issue: Issue) {
        val ref = db.child("issues").child(issue.userId)
        val id = ref.push().key ?: return
        ref.child(id).setValue(issue.copy(id = id))
    }

     fun updateIssue(issue: Issue) {
        db.child("issues")
            .child(issue.userId)
            .child(issue.id)
            .setValue(issue)
    }

     fun deleteIssue(issue: Issue) {
        db.child("issues")
            .child(issue.userId)
            .child(issue.id)
            .removeValue()
    }
}