package com.example.admin_start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button save_button;
    EditText et_college,et_name,et_date,et_branch,et_subject,et_link;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save_button = findViewById(R.id.btn_save);
        et_college = findViewById(R.id.et_college_name);
        et_name = findViewById(R.id.et_name);
        et_date = findViewById(R.id.et_date);
        et_branch = findViewById(R.id.et_Branch);
        et_subject = findViewById(R.id.et_Subject);
        et_link = findViewById(R.id.et_link);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("StudentData");

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                UploadData upload = new UploadData(
                        et_college.getText().toString()
                        ,et_name.getText().toString()
                        ,et_date.getText().toString()
                        ,et_branch.getText().toString()
                        ,et_subject.getText().toString()
                        ,et_link.getText().toString());
                String uploadId = mDatabaseRef.push().getKey();
                mDatabaseRef.child(uploadId).setValue(upload).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, " suscessful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "error occured ", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }
}
