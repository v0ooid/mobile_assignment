package my.edu.tarc.jobseek.headHunting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R


class HeadHuntingAdapter(
    val context: Context,
    private val listener: MyClickListener

) : RecyclerView.Adapter<HeadHuntingAdapter.ViewHolder>() {

    private var dataSet = ArrayList<Employee>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val candi = dataSet[position]
        holder.name.text = candi.username
        holder.field.text = candi.field
        holder.experience.text = candi.experience.toString()
        holder.location.text = candi.location

        holder.itemView.setOnClickListener {
            listener.onRecordClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateCandiList(candiList: List<Employee>){
        this.dataSet.clear()
        this.dataSet.addAll(candiList)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textViewCandiName)
        val field: TextView = itemView.findViewById(R.id.textViewCandiField)
        val experience: TextView = itemView.findViewById(R.id.textViewCandiExp)
        val location: TextView = itemView.findViewById(R.id.textViewCandiLocation)
    }

}
interface MyClickListener{
    fun onRecordClickListener(index: Int)
}



