package com.mgcoco.changethemesample;

import android.app.Application;
import android.os.Environment;
import android.widget.ProgressBar;

import com.mgcoco.changetheme.CustomViewAttributeApplyListener;
import com.mgcoco.changetheme.SkinCustomView;
import com.mgcoco.changetheme.SkinManager;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().loadSkinApk(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/skin.apk");

        CustomViewAttributeApplyListener listener = (view, fieldName, resId) -> {
            String viewName = view.getClass().getSimpleName();
            switch (viewName){
                case "ProgressBar":
                    ((ProgressBar)view.findViewById(R.id.progressBar)).setProgressDrawable(SkinManager.getInstance().getDrawable(resId));
                    break;
            }
        };
        SkinManager.getInstance().addCustomView(new SkinCustomView(ProgressBar.class.getSimpleName(), ProgressBar.class.getName(), new String[]{"progressDrawable"}, listener));
    }

}
