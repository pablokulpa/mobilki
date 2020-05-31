package pl.bien.sql_lite.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import pl.bien.sql_lite.model.Subject;
import pl.bien.sql_lite.model.TakenSubject;

import java.util.ArrayList;
import java.util.List;

import static pl.bien.sql_lite.util.Constants.*;

public class TakenSubjectQueryImplementation implements QueryContract.TakenSubjectQuery {

    private DatabaseHelper databaseHelper = DatabaseHelper.getInstance();

    @Override
    public void createTakenSubject(int studentId, int subjectId, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_ID_FK, studentId);
        contentValues.put(SUBJECT_ID_FK, subjectId);

        try {
            long rowCount = sqLiteDatabase.insertOrThrow(TABLE_STUDENT_SUBJECT, null, contentValues);

            if (rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Subject assign failed");

        } catch (SQLiteException e) {
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void readAllTakenSubjectByStudentId(int studentId, QueryResponse<List<Subject>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        String QUERY = "SELECT * FROM subject as s JOIN student_subject as ss ON s._id = ss.subject_id WHERE ss.student_id = " + studentId;
        Cursor cursor = null;
        try {
            List<Subject> subjectList = new ArrayList<>();
            cursor = sqLiteDatabase.rawQuery(QUERY, null);

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(SUBJECT_ID));
                    String subjectName = cursor.getString(cursor.getColumnIndex(SUBJECT_NAME));

                    Subject subject = new Subject(id, subjectName);
                    subjectList.add(subject);

                } while (cursor.moveToNext());

                response.onSuccess(subjectList);
            } else
                response.onFailure("There are no subject assigned to this student");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if (cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void readAllSubjectWithTakenStatus(int studentId, QueryResponse<List<TakenSubject>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        String QUERY = "SELECT s._id, s.name, ss.student_id " +
                "FROM subject as s LEFT JOIN student_subject as ss ON s._id = ss.subject_id " +
                "AND ss.student_id = " + studentId;

        Cursor cursor = null;
        try {
            List<TakenSubject> takenSubjectList = new ArrayList<>();
            cursor = sqLiteDatabase.rawQuery(QUERY, null);

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(SUBJECT_ID));
                    String subjectName = cursor.getString(cursor.getColumnIndex(SUBJECT_NAME));

                    boolean isTaken = false;

                    if(cursor.getInt(cursor.getColumnIndex(STUDENT_ID_FK)) > 0) {
                        isTaken = true;
                    }

                    TakenSubject takenSubject = new TakenSubject(id, subjectName, isTaken);
                    takenSubjectList.add(takenSubject);

                } while (cursor.moveToNext());

                response.onSuccess(takenSubjectList);
            } else
                response.onFailure("There are no subject assigned to this student");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if (cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void deleteTakenSubject(int studentId, int subjectId, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(TABLE_STUDENT_SUBJECT,
                    STUDENT_ID_FK + " =? AND " + SUBJECT_ID_FK + " =? ",
                    new String[]{String.valueOf(studentId), String.valueOf(subjectId)});

            if (rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Assigned subject deletion failed");

        } catch (Exception e) {
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }
}
