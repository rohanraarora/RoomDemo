package in.codingninjas.recyclerclass;

import android.content.Intent;
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
        Note note = new Note(mTitleEditText.getEditableText().toString(),
                mDescEditText.getEditableText().toString());
        Intent result = new Intent();
        result.putExtra("title",note.getTitle());
        result.putExtra("desc",note.getDescription());
        setResult(RESULT_OK,result);
        this.finish();
    }
}
