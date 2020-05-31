package pl.bien.sql_lite.features.subject_crud.subject_list_show;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import pl.bien.sql_lite.R;
import pl.bien.sql_lite.database.*;
import pl.bien.sql_lite.features.subject_crud.SubjectCrudListener;
import pl.bien.sql_lite.features.subject_crud.subject_create.*;
import pl.bien.sql_lite.model.*;

import java.util.ArrayList;
import java.util.List;

import static pl.bien.sql_lite.util.Constants.*;

public class SubjectListActivity extends AppCompatActivity implements SubjectCrudListener {

    private RecyclerView recyclerView;
    private TextView noDataFoundTextView;
    private TextView studentCountTextView;
    private TextView subjectCountTextView;
    private TextView takenSubjectCountTextView;
    private FloatingActionButton fab;

    private List<Subject> subjectList = new ArrayList<>();
    private SubjectListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initialization();

        adapter = new SubjectListAdapter(this, subjectList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        showSubjectList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubjectCreateDialogFragment dialogFragment = SubjectCreateDialogFragment.newInstance("Create Subject", SubjectListActivity.this);
                dialogFragment.show(getSupportFragmentManager(), CREATE_SUBJECT);

            }
        });
    }

    @Override
    public void onSubjectListUpdate(boolean isUpdate) {
        if(isUpdate) {
            showSubjectList();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    private void showSubjectList(){
        QueryContract.SubjectQuery query = new SubjectQueryImplementation();
        query.readAllSubject(new QueryResponse<List<Subject>>() {
            @Override
            public void onSuccess(List<Subject> data) {
                recyclerView.setVisibility(View.VISIBLE);
                noDataFoundTextView.setVisibility(View.GONE);

                subjectList.clear();
                subjectList.addAll(data);
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
