package com.hellohasan.sqlite_multiple_three_tables_crud.features.student_crud.student_list_show;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellohasan.sqlite_multiple_three_tables_crud.R;

class StudentViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    ImageView editImageView;
    ImageView deleteImageView;

    StudentViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        editImageView = itemView.findViewById(R.id.editImageView);
        deleteImageView = itemView.findViewById(R.id.deleteImageView);
    }
}
