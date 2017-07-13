package in.codingninjas.recyclerclass;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by rohan on 12/07/17.
 */

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Note(String title, String desc){
        this.title = title;
        this.description = desc;
    }

    public Note(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title.trim();
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Note> getRandomNotes(int noOfNotes){
        ArrayList<Note> notes = new ArrayList<>();
        for(int i = 0;i<noOfNotes;i++){
            Note note = new Note("Title "+ i,"Description " + i);
            notes.add(note);
        }
        return notes;
    }
}
