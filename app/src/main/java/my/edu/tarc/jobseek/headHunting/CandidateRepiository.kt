package my.edu.tarc.jobseek.headHunting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CandidateRepiository {

    val firebaseDatabase = Firebase.database
    val myRef = firebaseDatabase.getReference("Candidates")

    private var INSTANCE : CandidateRepiository ?= null

    fun getInstance() : CandidateRepiository {
        return INSTANCE ?: synchronized(this){
            val instance = CandidateRepiository()
            INSTANCE = instance
            instance
        }
    }

    fun loadCandi(candiList : MutableLiveData<List<Candidate>>){
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _candiList : List<Candidate> = snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(Candidate::class.java)!!
                    }
                    Log.d("_candiList", _candiList.toString())
                    candiList.postValue(_candiList)

                }catch (e : Exception){
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }
}