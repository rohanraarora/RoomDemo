package in.codingninjas.recyclerclass;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<Note> mNotes;
    RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = mNotes.size();
                Note note = new Note("Title " + size,"Description " + size);
                mNotes.add(note);
                mAdapter.notifyItemInserted(size);
                mRecyclerView.smoothScrollToPosition(size);
            }
        });

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mNotes = Note.getRandomNotes(5);
        mAdapter = new RecyclerAdapter(this, mNotes, new RecyclerAdapter.NotesClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Note note = mNotes.get(position);
                Snackbar.make(mRecyclerView,note.getTitle(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onRemoveClicked(int position) {
                mNotes.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mNotes,from,to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mNotes.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle vclicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mNotes.clear();
            mNotes.addAll(Note.getRandomNotes(5));
            mAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
