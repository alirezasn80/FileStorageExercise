package alireza.sn.fileexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorageActivity extends AppCompatActivity {
    EditText editText;
    TextView dirFileEx;
    File dirEx;
    public static final String FILE_NAME ="my_texts.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        editText = findViewById(R.id.editText);
        dirFileEx = findViewById(R.id.dir_file_ex);

        dirEx = new File(Environment.getExternalStorageDirectory(),"alireza_s_n");

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
        },1);

    }

    public void onClickSave(View view) {

        if (!dirEx.exists()){
            dirEx.mkdir();
            //Toast.makeText(this, "create a file in "+dirEx.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }

        File file = new File(dirEx,FILE_NAME);
        String text = editText.getText().toString();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes());

            editText.getText().clear();
            Toast.makeText(this, "save in "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickLoad(View view) {
        File file = new File(dirEx,FILE_NAME);
       // String text;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            while (bis.available()!=0)
                stringBuilder.append((char)bis.read());

            editText.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}