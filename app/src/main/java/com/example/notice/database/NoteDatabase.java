package com.example.notice.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notice.entities.Note;

@Database(entities = {Note.class}, version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao dao();


    private static volatile NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
