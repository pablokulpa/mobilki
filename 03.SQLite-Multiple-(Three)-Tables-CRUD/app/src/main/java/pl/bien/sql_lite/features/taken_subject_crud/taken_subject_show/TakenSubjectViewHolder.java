package pl.bien.sql_lite.features.taken_subject_crud.taken_subject_show;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.bien.sql_lite.R;

class TakenSubjectViewHolder extends RecyclerView.ViewHolder {

    TextView subjectNameTextView;
    TextView courseCodeTextView;
    TextView creditTextView;
    ImageView deleteIcon;

    public TakenSubjectViewHolder(View itemView) {
        super(itemView);

        subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
        deleteIcon = itemView.findViewById(R.id.deleteIcon);
    }
}
