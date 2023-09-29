package my.edu.tarc.jobseek.headHunting

import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.jobseek.R
import org.w3c.dom.DocumentType
import org.w3c.dom.Text

class MyAdapter(
    private val context: Context,
    private val recordClickListener: RecordClickListener
) :
    RecyclerView.Adapter<MyAdapter.CandiViewHolder>() {
    private var candList = emptyList<Candidate>()

    class CandiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textViewCandiName)
        val field: TextView = itemView.findViewById(R.id.textViewCandiField)
        val experience: TextView = itemView.findViewById(R.id.textViewCandiExp)
        val location: TextView = itemView.findViewById(R.id.textViewCandiLocation)
    }

    internal fun setCandi(candiList: List<Candidate>) {
        this.candList = candiList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)
        return CandiViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandiViewHolder, position: Int) {
        val candi = candList[position]
        holder.name.text = candi.name
        holder.field.text = candi.field
        holder.experience.text = candi.experience.toString()
        holder.location.text = candi.location
        holder.itemView.setOnClickListener {
        }


    }

    override fun getItemCount(): Int {
        return candList.size
    }
}

    // onClickListener Interface
    interface RecordClickListener {
        fun onRecordClickListener(index: Int)
    }
