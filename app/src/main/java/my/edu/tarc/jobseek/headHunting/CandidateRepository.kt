package my.edu.tarc.jobseek.headHunting

import android.util.Log

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CandidateRepository {

    val firebaseDatabase = Firebase.database
    val myRef = firebaseDatabase.getReference("Candidates").child("Employee")

    private var INSTANCE : CandidateRepository ?= null

    fun getInstance() : CandidateRepository {
        return INSTANCE ?: synchronized(this){
            val instance = CandidateRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadCandi(candiList : MutableLiveData<List<Employee>>){
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val employeeList = mutableListOf<Employee>()
                    Log.d("_candiList", employeeList.toString())


                    for (employeeSnapshot in snapshot.children) {
                        val employee = employeeSnapshot.getValue(Employee::class.java)
                        if (employee != null) {
                            // Populate the name property from the snapshot key
                            employee.username = employeeSnapshot.key ?: ""
                            employeeList.add(employee)
                        }
                    }

                    Log.d("_candiList", employeeList.toString())
                    candiList.postValue(employeeList)

                }catch (e : Exception){
                    Log.d("tag", e.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }
}