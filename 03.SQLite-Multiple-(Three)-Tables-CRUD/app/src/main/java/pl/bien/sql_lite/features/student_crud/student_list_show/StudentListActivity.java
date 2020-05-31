package pl.bien.sql_lite.features.student_crud.student_list_show;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pl.bien.sql_lite.R;
import pl.bien.sql_lite.database.*;
import pl.bien.sql_lite.features.student_crud.student_create.StudentCreateDialogFragment;
import pl.bien.sql_lite.features.student_crud.StudentCrudListener;
import pl.bien.sql_lite.features.subject_crud.subject_list_show.SubjectListActivity;
import pl.bien.sql_lite.model.Student;
import pl.bien.sql_lite.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity implements StudentCrudListener {

    private RecyclerView recyclerView;
    private TextView noDataFoundTextView;
    private FloatingActionButton fab;
    private TextView studentCountTextView;
    private TextView subjectCountTextView;
    private TextView takenSubjectCountTextView;

    private List<Student> studentList = new ArrayList<>();
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialization();

        adapter = new StudentListAdapter(this, studentList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        showStudentList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentCreateDialogFragment studentCreateDialogFragment = StudentCreateDialogFragment.newInstance("Create Student", StudentListActivity.this);
                studentCreateDialogFragment.show(getSupportFragmentManager(), Constants.CREATE_STUDENT);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_show_subject){
            startActivity(new Intent(this, SubjectListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStudentListUpdate(boolean isUpdated) {
        if(isUpdated) {
            showStudentList();
        }
    }

    private void showStudentList() {
        QueryContract.StudentQuery query = new StudentQueryImplementation();
        query.readAllStudent(new QueryResponse<List<Student>>() {
            @Override
            public void onSuccess(List<Student> data) {
                recyclerView.setVisibility(View.VISIBLE);
                noDataFoundTextView.setVisibility(View.GONE);

                studentList.clear();
                studentList.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                recyclerView.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initialization(){
        recyclerView = findViewById(R.id.recyclerView);
        noDataFoundTextView = findViewById(R.id.noDataFoundTextView);
        fab = findViewById(R.id.fab);

    }
}
