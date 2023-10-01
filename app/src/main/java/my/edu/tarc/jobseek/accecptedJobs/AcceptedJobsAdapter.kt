package my.edu.tarc.jobseek.accecptedJobs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.jobseek.headHunting.Employee
import my.edu.tarc.jobseek.R

class AcceptedJobsAdapter(private val context: Context) : RecyclerView.Adapter<AcceptedJobsAdapter.ViewHolder>() {

    private var pendingEmployees: List<Pair<String, Employee>> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.accecpted_job_layout, parent, false)
        return ViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:    Int) {
        val pair = pendingEmployees[position]
        holder.bind(pair)
    }

    override fun getItemCount(): Int {
        return pendingEmployees.size
    }

    fun setPendingEmployees(pendingEmployees: List<Pair<String, Employee>>) {
        this.pendingEmployees = pendingEmployees
        notifyDataSetChanged()
    }

    fun removeItemAt(position: Int) {
        if (position in 0 until pendingEmployees.size) {
            val newList = pendingEmployees.toMutableList()
            newList.removeAt(position)
            pendingEmployees = newList
            notifyItemRemoved(position)
        }
    }

    class ViewHolder(itemView: View, private val adapter: AcceptedJobsAdapter) : RecyclerView.ViewHolder(itemView) {
        val jobApplied: TextView = itemView.findViewById(R.id.textViewjobApplied)
        val name: TextView = itemView.findViewById(R.id.textViewjobAppliedName)
        val field: TextView = itemView.findViewById(R.id.textViewjobAppliedField)
        val experience: TextView = itemView.findViewById(R.id.textViewjobAppliedExp)
        val location: TextView = itemView.findViewById(R.id.textViewjobAppliedLocation)
        val accept: ImageView = itemView.findViewById(R.id.imageButtonAccept)
        val decline: ImageView = itemView.findViewById(R.id.imageButtonDecline)

        fun bind(pair: Pair<String, Employee>) {
            val jobTitle = pair.first
            val employee = pair.second

            name.text = employee.username
            field.text = employee.field
            experience.text = employee.experience
            location.text = employee.location
            jobApplied.text = jobTitle
        }

        init {
            val candidateName = "Ching E-Minn"
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("Jobs")
                .child("ALIBINBABA")
                .child("Software Engineering")
                .child("Candidate")

            // Set click listeners for accept and decline buttons
            accept.setOnClickListener {
                reference.child(candidateName).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (childSnapshot in snapshot.children) {
                            if (childSnapshot.value == "pending") {
                                reference.child(candidateName).child("status")
                                    .setValue("approved")

                                val position = adapterPosition
                                if (position != RecyclerView.NO_POSITION) {
                                    adapter.removeItemAt(position)
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle onCancelled event if needed
                    }
                })
            }

            decline.setOnClickListener {
                reference.child(candidateName).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (childSnapshot in snapshot.children) {
                            if (childSnapshot.value == "pending") {
                                reference.child(candidateName).child("status")
                                    .setValue("denied")

                                val position = adapterPosition
                                if (position != RecyclerView.NO_POSITION) {
                                    adapter.removeItemAt(position)
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle onCancelled event if needed
                    }
                })
            }
        }
    }
}
