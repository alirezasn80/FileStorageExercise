package alireza.sn.fileexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {
    EditText editText;
    public static final String NAME_FILE ="example.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        editText = findViewById(R.id.editText);
    }

    public void onClickSave(View view) {
        FileOutputStream fos = null;
        String text = editText.getText().toString();
        if (text.isEmpty()){
            editText.setError("field can not be empty");
            return;
        }
        try {
            fos = openFileOutput(NAME_FILE,MODE_PRIVATE);
            fos.write(text.getBytes());

            Toast.makeText(this, "saved in "+getFilesDir()+"/"+NAME_FILE, Toast.LENGTH_SHORT).show();
            editText.getText().clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
          finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onClickLoad(View view) {
        FileInputStream fis = null;
        try {
            String text;
            fis = openFileInput(NAME_FILE);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((text = br.readLine())!=null){
                stringBuilder.append(text).append("\n");
            }

            editText.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}