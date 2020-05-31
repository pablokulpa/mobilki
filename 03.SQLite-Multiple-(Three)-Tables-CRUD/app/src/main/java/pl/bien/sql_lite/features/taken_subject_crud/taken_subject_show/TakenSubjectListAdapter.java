package pl.bien.sql_lite.features.taken_subject_crud.taken_subject_show;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pl.bien.sql_lite.R;
import pl.bien.sql_lite.database.QueryContract;
import pl.bien.sql_lite.database.QueryResponse;
import pl.bien.sql_lite.database.TakenSubjectQueryImplementation;
import pl.bien.sql_lite.features.taken_subject_crud.TakenSubjectCrudListener;
import pl.bien.sql_lite.model.Subject;

import java.util.List;

public class TakenSubjectListAdapter extends RecyclerView.Adapter<TakenSubjectViewHolder> {

    private Context context;
    private int studentId;
    private List<Subject> subjectList;
    private TakenSubjectCrudListener listener;

    public TakenSubjectListAdapter(Context context, int studentId, List<Subject> subjectList, TakenSubjectCrudListener listener) {
        this.context = context;
        this.studentId = studentId;
        this.subjectList = subjectList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TakenSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_taken_subject, parent, false);
        return new TakenSubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TakenSubjectViewHolder holder, int position) {
        final int itemPos = position;
        final Subject subject = subjectList.get(position);

        holder.subjectNameTextView.setText(subject.getName());
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAssignedSubject(subject.getId(), itemPos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    private void removeAssignedSubject(int subjectId, final int itemPosition) {
        QueryContract.TakenSubjectQuery query = new TakenSubjectQueryImplementation();
        query.deleteTakenSubject(studentId, subjectId, new QueryResponse<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if(data){
                    subjectList.remove(itemPosition);
                    notifyDataSetChanged();
                    listener.onTakenSubjectUpdated(true);
                } else
                    Toast.makeText(context, "Failed to remove subject", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
