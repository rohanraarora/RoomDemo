package in.codingninjas.recyclerclass;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by rohan on 13/07/17.
 */


@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();


    @Query("SELECT * FROM notes WHERE title = :title AND description = :desc")
    List<Note> getAllNotesWithName(String title,String desc);

    @Insert
    void insertNote(Note note);

}
