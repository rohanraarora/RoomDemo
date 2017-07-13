package in.codingninjas.recyclerclass;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by rohan on 13/07/17.
 */


@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase INSTANCE;

    private static Object LOCK = new Object();

    public static NoteDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (LOCK){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            ,NoteDatabase.class,NoteDatabase.DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final String DB_NAME = "notes_db";

    abstract NoteDao noteDao();

}
