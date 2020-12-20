package alireza.sn.fileexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickInternal(View view) {
        startActivity(new Intent(this,InternalStorageActivity.class));
    }

    public void onClickExternal(View view) {
        startActivity(new Intent(this,ExternalStorageActivity.class));
    }
}