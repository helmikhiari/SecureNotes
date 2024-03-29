package com.example.notice.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notice.R;
import com.example.notice.entities.Note;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private List<Note> source;
    private NoteListener noteListener;
    private Timer timer;

    public NoteAdapter(List<Note> notes, NoteListener noteListener) {
        this.notes = notes;
        this.source = notes;
        this.noteListener = noteListener;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setNote(notes.get(position));

        holder.noteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noteListener.onNoteClicked(notes.get(position), position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteContent;
        ConstraintLayout noteLayout;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title_tv);
            noteContent = itemView.findViewById(R.id.note_content_tv);

            noteLayout = itemView.findViewById(R.id.note_layout);
        }


        void setNote(Note note) {
            noteTitle.setText(note.getTitle());
            if (note.getContent().trim().isEmpty()) {
                noteContent.setVisibility(View.GONE);
            } else {
                noteContent.setText(note.getContent());
            }

        }
    }


    public void search(String text) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (text.trim().isEmpty()) {
                    notes = source;
                } else {
                    ArrayList<Note> list = new ArrayList<>();
                    for (Note item : source) {
                        if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                            list.add(item);
                        }
                    }
                    notes = list;
                }

                new Handler(Looper.getMainLooper()) {
                }.post(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }, 300);
    }

}