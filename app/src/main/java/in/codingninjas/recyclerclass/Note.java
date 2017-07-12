package in.codingninjas.recyclerclass;

import java.util.ArrayList;

/**
 * Created by rohan on 12/07/17.
 */

public class Note {

    private String title;
    private String description;

    public Note(String title,String desc){
        this.title = title;
        this.description = desc;
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
