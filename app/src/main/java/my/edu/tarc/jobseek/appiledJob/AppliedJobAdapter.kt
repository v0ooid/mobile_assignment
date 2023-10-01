import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R
import my.edu.tarc.jobseek.home.JobModel

class AppliedJobAdapter(private var appliedJobList: MutableList<JobModel>): RecyclerView.Adapter<AppliedJobAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val jobTitle: TextView = itemView.findViewById(R.id.textViewJobTitle)
        val companyName: TextView = itemView.findViewById(R.id.textViewCompanyName)
        val status: TextView = itemView.findViewById(R.id.textViewStatus)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recycler_view_applied_jobs, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return appliedJobList.size
    }

    fun updateData(newData: MutableList<JobModel>) {
        appliedJobList.clear() // Clear the existing data
        appliedJobList.addAll(newData) // Add the new data
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = appliedJobList[position]

        //Log.e("TAG", currentItem.toString())

        holder.jobTitle.text = currentItem.job_title
        holder.companyName.text = currentItem.company_name
        holder.status.text = currentItem.apply_status

        //Log.d("Testinggg", "Company Name: ${holder.jobTitle.text}, Job Title: ${holder.companyName.text}, Status: ${ holder.status.text}")

    }
}
