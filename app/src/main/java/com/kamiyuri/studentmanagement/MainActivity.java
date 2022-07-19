package com.kamiyuri.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.kamiyuri.studentmanagement.Database.SQLite;
import com.kamiyuri.studentmanagement.Model.Student;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            students = SQLite.getInstance(this).getStudents();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!students.isEmpty()) {
            for (Student student : students) {
                student.printData();
            }
        }
    }
}