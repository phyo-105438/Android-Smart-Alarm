package it.ezzie.smartalarm;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    public AlarmAdapter(){

    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder{

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
