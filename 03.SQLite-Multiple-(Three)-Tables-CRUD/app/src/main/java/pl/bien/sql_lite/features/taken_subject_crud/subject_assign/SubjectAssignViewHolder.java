package pl.bien.sql_lite.features.taken_subject_crud.subject_assign;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import pl.bien.sql_lite.R;

public class SubjectAssignViewHolder extends RecyclerView.ViewHolder {

    CheckBox checkBox;
    TextView subjectNameTextView;

    public SubjectAssignViewHolder(View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.checkbox);
        subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
    }
}
