package my.edu.tarc.jobseek.accecptedJobs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.headHunting.Candidate

class AcceptedJobsAdapter(
    val context: Context
) : RecyclerView.Adapter<AcceptedJobsAdapter.ViewHolder>(){

    private var dataSet = ArrayList<Candidate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.accecpted_job_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val accept = dataSet[position]
        holder.jobApplied.text = null
        holder.name.text = null
        holder.field.text = null
        holder.experience.text = null
        holder.location.text = null

        holder.decline.setOnClickListener{

        }

        holder.accept.setOnClickListener{

        }
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val jobApplied: TextView = itemView.findViewById(R.id.textViewjobApplied)
        val name: TextView = itemView.findViewById(R.id.textViewAppliedJobName)
        val field: TextView = itemView.findViewById(R.id.textViewAppliedJobField)
        val experience: TextView = itemView.findViewById(R.id.textViewAppliedJobExp)
        val location: TextView = itemView.findViewById(R.id.textViewAppliedJobLocation)
        val decline: ImageView = itemView.findViewById(R.id.imageViewDecline)
        val accept: ImageView = itemView.findViewById(R.id.imageViewAccept)

    }

}

