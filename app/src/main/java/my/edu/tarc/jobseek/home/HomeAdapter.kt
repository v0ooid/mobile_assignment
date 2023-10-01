package my.edu.tarc.jobseek.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R

class HomeAdapter(private var jobList : List<JobModel>, private val listener: MyClickListener): RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val jobImage : ImageView = itemView.findViewById(R.id.imageViewJobImage)
        val jobTitle : TextView = itemView.findViewById(R.id.textViewJobTitle)
        val companyName : TextView = itemView.findViewById(R.id.textViewCompanyName)
        val emType : TextView = itemView.findViewById(R.id.textViewEmType)
        val salary : TextView = itemView.findViewById(R.id.textViewStatus)
        val jobSpecialization : TextView = itemView.findViewById(R.id.textViewJobSpecial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_jobs,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jobList[position]
        //val currentImage = employerList[position]

        holder.jobTitle.text = currentItem.job_title
        holder.companyName.text = currentItem.company_name
        holder.emType.text = currentItem.employment_type
        holder.salary.text = currentItem.salary.toString()
        holder.jobSpecialization.text = currentItem.job_specialization
        currentItem.job_responsibility
        currentItem.qualification
        currentItem.skills
        currentItem.year_of_experience.toString()
        currentItem.apply_status

        holder.itemView.setOnClickListener{
            listener.onRecordClickListener(position)
        }

    }

    override fun getItemCount(): Int {
        return jobList.size
    }


}

interface MyClickListener{
    fun onRecordClickListener(Index: Int)
}

