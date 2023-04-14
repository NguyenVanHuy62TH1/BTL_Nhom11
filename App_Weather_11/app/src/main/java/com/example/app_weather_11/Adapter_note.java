package com.example.app_weather_11;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter_note extends RecyclerView.Adapter<Adapter_note.ViewHoder> {

    LayoutInflater inflater;
    List<NoteModel> noteModels;

    Adapter_note(Context context, List<NoteModel> noteModels){
        this.inflater = LayoutInflater.from(context);
        this.noteModels = noteModels;
    }

    @NonNull
    @Override
    public Adapter_note.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_view, parent, false);
        return  new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_note.ViewHoder holder, int position) {

        String title = noteModels.get(position).getNoteTitle();
        String date = noteModels.get(position).getNoteDate();
        String time = noteModels.get(position).getNoteTime();

        holder.nTitle.setText(title);
        holder.nTime.setText(time);
        holder.nDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        TextView nTitle, nDate, nTime;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            nTitle = itemView.findViewById(R.id.nTitle);
            nTime = itemView.findViewById(R.id.nTime);
            nDate = itemView.findViewById(R.id.nDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("ID",noteModels.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
