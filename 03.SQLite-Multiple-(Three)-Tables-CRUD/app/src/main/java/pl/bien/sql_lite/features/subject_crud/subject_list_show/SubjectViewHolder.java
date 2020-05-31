package pl.bien.sql_lite.features.subject_crud.subject_list_show;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.bien.sql_lite.R;

public class SubjectViewHolder extends RecyclerView.ViewHolder {

    TextView subjectNameTextView;
    TextView courseCodeTextView;
    TextView creditTextView;
    ImageView editIcon;
    ImageView deleteIcon;

    public SubjectViewHolder(View itemView) {
        super(itemView);

        subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
        editIcon = itemView.findViewById(R.id.editIcon);
        deleteIcon = itemView.findViewById(R.id.deleteIcon);
    }
}
