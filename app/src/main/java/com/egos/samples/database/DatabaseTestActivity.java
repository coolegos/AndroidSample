package com.egos.samples.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.egos.samples.R;

/**
 * Created by Egos on 2016/12/27.
 */
public class DatabaseTestActivity extends AppCompatActivity {

    private EditText mStudentId;
    private EditText mStudentName;
    private EditText mStudentAge;

    private StudentDatabase mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mStudentId = (EditText) findViewById(R.id.student_id);
        mStudentName = (EditText) findViewById(R.id.student_name);
        mStudentAge = (EditText) findViewById(R.id.student_age);
        mDatabase = new StudentDatabase(this);
    }

    public void addStudent(View view) {
        if (mStudentId.getEditableText() != null &&
                mStudentId.getEditableText().toString() != null &&
                mStudentId.getEditableText().toString().trim() != null &&
                mStudentName.getEditableText() != null &&
                mStudentName.getEditableText().toString() != null &&
                mStudentName.getEditableText().toString().trim() != null &&
                mStudentAge.getEditableText() != null &&
                mStudentAge.getEditableText().toString() != null &&
                mStudentAge.getEditableText().toString().trim() != null) {
            mDatabase.insert(mStudentId.getEditableText().toString().trim(),
                    mStudentName.getEditableText().toString().trim(),
                    Integer.valueOf(mStudentAge.getEditableText().toString().trim()));
        }
    }

    public void getStudentList(View view) {
        Toast.makeText(this, "数据库学生数量：" + mDatabase.query(), Toast.LENGTH_SHORT).show();
    }
}