package my.edu.tarc.jobseek.accecptedJobs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.jobseek.headHunting.Candidate
import my.edu.tarc.jobseek.headHunting.CandidateRepiository

class acceptJobRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Jobs")

    companion object {
        @Volatile
        private var INSTANCE: acceptJobRepository? = null

        fun getInstance(): acceptJobRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = acceptJobRepository()
                INSTANCE = instance
                instance
            }
        }
    }

    fun loadCandi(candiList: MutableLiveData<List<Candidate>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _candiList: MutableList<Candidate> = mutableListOf()

                    for (companySnapshot in snapshot.children) {
                        for (jobSnapshot in companySnapshot.children) {
                            val candidateSnapshot = jobSnapshot.child("candidate")
                            for (candidateChildSnapshot in candidateSnapshot.children) {
                                val status = candidateChildSnapshot.child("status").getValue(String::class.java)
                                if (status == "pending") {
                                    // Create a Candidate object and add it to the list
                                    val candidate = Candidate(
                                        // Populate candidate properties here based on the snapshot
                                    )
                                    _candiList.add(candidate)
                                }
                            }
                        }
                    }

                    Log.d("_candiList", _candiList.toString())
                    candiList.postValue(_candiList)

                } catch (e: Exception) {
                    // Handle exceptions
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database errors
                Log.e("Firebase", "Database Error: ${error.message}")
            }
        })
    }

}