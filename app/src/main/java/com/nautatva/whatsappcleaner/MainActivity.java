package com.nautatva.whatsappcleaner;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
//        else {
//            Toast.makeText(MainActivity.this,"Permission already granted", Toast.LENGTH_SHORT).show();
//        }
    }

    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.exists()) {
            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    deleteRecursive(child);
            fileOrDirectory.delete();
        }
        else {
            Toast.makeText(MainActivity.this,"Folder does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    public void cleanWhatsappDatabases(View view){
        checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, 2);
        String path = "/WhatsApp/Databases";
        File whatsappDatabases = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path);
        Log.d("check: ",whatsappDatabases.toString());
        if (whatsappDatabases.listFiles() != null)
            deleteRecursive(whatsappDatabases);
        else{
            Toast.makeText(MainActivity.this,"Folder does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
