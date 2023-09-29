package my.edu.tarc.jobseek.job_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.job_detail.JobDetailFragment

class JobAdapter(private val jobList : List<Job>, private val listener:MyClickListener): RecyclerView.Adapter<JobAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val jobTitle : TextView = itemView.findViewById(R.id.textViewJobTitle)
        //val jobSpecialization:TextView = itemView.findViewById(R.id.spinnerJobSpecialization)
        val employmentType : TextView = itemView.findViewById(R.id.textViewEmploymentType)
        //val jobResponsibility:TextView=itemView.findViewById(R.id.editTextJobResponsibility)
        //val skills:TextView=itemView.findViewById(R.id.editTextSkills)
        //val qualification:TextView=itemView.findViewById(R.id.editTextQualification)
       // val yearOfExperience:TextView=itemView.findViewById(R.id.editTextYearExperience)
        val salary : TextView = itemView.findViewById(R.id.textViewSalary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_posted,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jobList[position]

        holder.jobTitle.text = currentItem.job_title
        //holder.skills.text = currentItem.skills
        holder.employmentType.text = currentItem.employment_type
        holder.salary.text = currentItem.salary.toString()

        holder.itemView.setOnClickListener{
            listener.onRecordClickListener(position)
        }

        }

    override fun getItemCount(): Int {
        return jobList.size
    }


}

interface MyClickListener{
    fun onRecordClickListener(index:Int)
}