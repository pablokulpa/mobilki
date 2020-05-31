package pl.kulpa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class StudentListRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {


    DAO dao;
    private Context context;
    private List<Student> studentList;
    //private DatabaseQueryClass databaseQueryClass;

    public StudentListRecyclerViewAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final int itemPosition = position;
        final Student student = studentList.get(position);

        holder.nameTextView.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
