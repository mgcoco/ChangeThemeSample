package com.mgcoco.changethemesample;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.mgcoco.changetheme.AttributeName;
import com.mgcoco.changetheme.AttributeTypeName;
import com.mgcoco.changetheme.SkinManager;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().inflate(getLayoutInflater(), getLifecycle());
        setContentView(R.layout.fragment_second);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.button_second).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.clear_theme).setOnClickListener(v -> {
            SkinManager.getInstance().clearTheme();
        });

        findViewById(R.id.change_theme).setOnClickListener(v -> {
            SkinManager.getInstance().loadSkinApk(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/skin.apk");
            SkinManager.getInstance().apply();
        });

        SkinManager.getInstance().putDynamicGroup(findViewById(R.id.textview_second), AttributeName.ATTRIBUTE_TEXTCOLOR, AttributeTypeName.TYPE_COLOR,  R.color.teal_700);

        ArrayList<ItemSample> data = new ArrayList<>();
        data.add(new ItemSample(R.drawable.icon_trash, "trash icon"));
        data.add(new ItemSample(R.drawable.icon_email, "email icon"));
        data.add(new ItemSample(R.drawable.icon_edit, "edit icon"));
        data.add(new ItemSample(R.drawable.icon_gift, "gift icon"));
        data.add(new ItemSample(R.drawable.icon_location, "location icon"));
        data.add(new ItemSample(R.drawable.icon_setting, "setting icon"));
        data.add(new ItemSample(R.drawable.icon_search, "search icon"));
        ((RecyclerView)findViewById(R.id.list)).setAdapter(new MyAdapter(this, data));
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
}