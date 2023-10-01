package my.edu.tarc.jobseek.accecptedJobs

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.headHunting.CandidateRepository
import my.edu.tarc.jobseek.headHunting.Employee

class acceptJobRepository {

    private val database = FirebaseDatabase.getInstance()

    private var INSTANCE : acceptJobRepository?= null

    fun getInstance() : acceptJobRepository {
        return INSTANCE ?: synchronized(this){
            val instance = acceptJobRepository()
            INSTANCE = instance
            instance
        }
    }


    fun loadPendingEmployees(employeeList :  MutableLiveData<List<Pair<String, Employee>>>) {
        // Replace "Kaion Enterprise" with the dynamically obtained company name
        val currentEmployerCompanyName = "Kaion Enterprise" // Replace with actual company name

        val companyJobsRef = database.reference.child("Jobs").child("ALIBINBABA")

        val filteredEmployees = mutableListOf<Pair<String, Employee>>()

        companyJobsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (jobSnapshot in dataSnapshot.children) {
                    val jobTitle = jobSnapshot.key // Get the job title
                    val employeeApplicationsRef = jobSnapshot.child("Candidate")
                    for (employeeSnapshot in employeeApplicationsRef.children) {
                        val status = employeeSnapshot.child("status").getValue(String::class.java)
                        if (status == "applied") {
                            val employeeName = employeeSnapshot.key.toString()
                            Log.e("Empname", employeeName)
                            val employeeDataRef = database.reference.child("Candidates").child("Employee").child(employeeName)
                            Log.e("emp Ref", employeeDataRef.toString())
                            employeeDataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(employeeDataSnapshot: DataSnapshot) {
                                    val employee = employeeDataSnapshot.getValue(Employee::class.java)
                                    Log.e("checking", employee.toString())
                                    if (employee != null) {
                                        // Add the job title along with the employee
                                        filteredEmployees.add(Pair(jobTitle!!, employee))
                                        // Update LiveData with the filtered employees
                                        employeeList.postValue(filteredEmployees)
                                        Log.e("Repo", employeeList.toString())
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    // Handle database error if needed
                                }
                            })
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error if needed
            }
        })
    }

}
