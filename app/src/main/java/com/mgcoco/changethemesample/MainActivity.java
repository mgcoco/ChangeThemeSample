package com.mgcoco.changethemesample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mgcoco.changetheme.AttributeName;
import com.mgcoco.changetheme.AttributeTypeName;
import com.mgcoco.changetheme.SkinManager;

public class MainActivity extends AppCompatActivity {

    private TextView textStatus;

    private int status = (int)(Math.random() * 3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().inflate(getLayoutInflater(), getLifecycle());
        setContentView(R.layout.fragment_first);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.button_first).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(i);
        });

        findViewById(R.id.change_theme).setOnClickListener(v -> {
            SkinManager.getInstance().loadSkinApk(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/skin.apk");
            SkinManager.getInstance().apply();
        });

        textStatus = findViewById(R.id.status);
        new Handler(Looper.myLooper()).postDelayed(() -> {
            SkinManager.getInstance().putDynamicGroup(textStatus, AttributeName.ATTRIBUTE_TEXTCOLOR, AttributeTypeName.TYPE_COLOR, getStatusColor(status));
        }, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getStatusColor(int status){
        switch (status){
            case 0:
                return R.color.colorPrimary;
            case 1:
                return R.color.colorPrimaryDark;
            default:
                return R.color.colorSecondary;
        }
    }
}