package pl.kulpa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAO {



    public DatabaseHelper databaseHelper;

    public DAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public List<Student> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        ArrayList<Student> studentList = new ArrayList<>();

        Cursor cursor = db.rawQuery(getAllQuery(), null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }


    public void addStudent(Student student) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        //Content values use KEY-VALUE pair concept
        ContentValues values = new ContentValues();
        values.put(Config.COLUMN_STUDENT_ID, student.getId());
        values.put(Config.COLUMN_STUDENT_NAME, student.getName());

        db.insert(Config.TABLE_STUDENT, null, values);
        db.close();
    }

    public String getAllQuery(){
        return  "SELECT  " +
                Config.COLUMN_STUDENT_ID + "," +
                Config.COLUMN_STUDENT_NAME +
                " FROM " + Config.TABLE_STUDENT;
    }

}
