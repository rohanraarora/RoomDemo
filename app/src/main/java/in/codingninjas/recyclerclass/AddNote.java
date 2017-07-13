package in.codingninjas.recyclerclass;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mTitleEditText = (EditText) findViewById(R.id.titleEditText);
        mDescEditText = (EditText) findViewById(R.id.descEditText);
    }

    public void addNote(View view){
        final Note note = new Note(mTitleEditText.getEditableText().toString(),
                mDescEditText.getEditableText().toString());
        NoteDatabase db = NoteDatabase.getInstance(this);
        final NoteDao dao = db.noteDao();
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                 dao.insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Intent result = new Intent();
                result.putExtra("title",note.getTitle());
                result.putExtra("desc",note.getDescription());
                setResult(RESULT_OK,result);
                AddNote.this.finish();

            }
        }.execute();

    }
}
