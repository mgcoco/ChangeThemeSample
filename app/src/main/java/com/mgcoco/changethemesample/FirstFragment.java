package com.mgcoco.changethemesample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mgcoco.changetheme.SkinManager;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        SkinManager.getInstance().inflate(inflater);
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SkinManager.getInstance().apply();
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                int status = (int)(Math.random() * 3);
                ((TextView)view.findViewById(R.id.status)).setText("Set color dynamically with status " + status);
                ((TextView)view.findViewById(R.id.status)).setTextColor(getColor(status));
            }
        }, 1000);

    }

    private int getColor(int status){
        switch (status){
            case 0:
                return SkinManager.getInstance().getColor(R.color.colorPrimary);
            case 1:
                return SkinManager.getInstance().getColor(R.color.colorPrimaryDark);
            default:
                return SkinManager.getInstance().getColor(R.color.colorSecondary);
        }
    }
}