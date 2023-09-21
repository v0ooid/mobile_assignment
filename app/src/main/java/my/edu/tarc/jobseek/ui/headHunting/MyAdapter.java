package my.edu.tarc.jobseek.ui.headHunting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.edu.tarc.jobseek.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Candidate> candidates;

    public MyAdapter(Context context, List<Candidate> candidates) {
        this.context = context;
        candidates = candidates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_row,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewName.setText(candidates.get(position).getName());
        holder.textViewSkills.setText(candidates.get(position).getSkills().toString());
        holder.textViewName.setText(candidates.get(position).getPosition());
        holder.textViewName.setText(candidates.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }
}
