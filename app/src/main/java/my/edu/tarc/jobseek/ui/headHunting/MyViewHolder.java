package my.edu.tarc.jobseek.ui.headHunting;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.edu.tarc.jobseek.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName, textViewPosition, textViewLocation, textViewSkills;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewPosition = itemView.findViewById(R.id.textViewPosition);
        textViewSkills = itemView.findViewById(R.id.textViewSkills);
        textViewLocation = itemView.findViewById(R.id.textViewLocation);

    }



}
