package com.example.storage_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText filename;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename = findViewById(R.id.filename);
        text = findViewById(R.id.content);
    }
    private boolean isExternalStorageWritable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("State", "Yes, it is writable!");
            return true;
        }else{
            return false;
        }
    }

//    public void writeFile(View v){
//        if(isExternalStorageWritable() && checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
//            File textFile = new File(Environment.getExternalStorageDirectory(), filename.getText().toString());
//            try {
//                FileOutputStream fos = new FileOutputStream(textFile);
//                fos.write(text.getText().toString().getBytes());
//                fos.close();
//                Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//
//        }
//        else{
//            Toast.makeText(this, "Cannot Write to External Storage..", Toast.LENGTH_SHORT).show();
//        }
//    }
    public void writeFile(View view){
        String message = text.getText().toString();
        try {
            FileOutputStream file = openFileOutput(filename.getText().toString(), MODE_PRIVATE);
            file.write(text.getText().toString().getBytes());
            file.close();
            Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
                e.printStackTrace();
        }

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}