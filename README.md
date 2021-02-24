# ChangeTheme
Follow the rules to build your own themeable app.  
This library will help you to change themes fastly.  
# ScreenShoot
![Skin Resource](/resource/sample_skin_resource_compare.png)
![Effect of skin](/resource/screenshot_compare.png)
# Import
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
```
implementation 'com.github.mgcoco:ChangeTheme:version'
```

# Usage
(Required) Initial skin manger and setup skin apk path.
```
SkinManager.getInstance().init(this);
String path = "Change to your APK path";
SkinManager.getInstance().loadSkinApk(path);
```
(Required) Put this line before setContentView method.
```
SkinManager.getInstance().inflate(getLayoutInflater());
```
(Required) Apply after view created or skin changed.
```
SkinManager.getInstance().apply();
```
### Customize
Supported third party views or fields
- Put view name, field names and callback listener through addCustomView method.
- CustomViewAttributeApplyListener will be called after apply. You should setup attributes in the callback.
```
CustomViewAttributeApplyListener listener = (view, fieldName, resId) -> {
    String viewName = view.getClass().getSimpleName();
    switch (viewName){
    case "ProgressBar":
        ((ProgressBar)view.findViewById(R.id.progressBar)).setProgressDrawable(SkinManager.getInstance().getDrawable(resId));
        break;
    }
};
SkinManager.getInstance().addCustomView(new SkinCustomView(ProgressBar.class.getSimpleName(), ProgressBar.class.getName(), new String[]{"progressDrawable"}, listener));
```
### Dynamic configuration
Use SkingManager methods to get resources from target skin APK.
```
SkinManager.getInstance().getColor(R.color.colorPrimary);
SkinManager.getInstance().getDrawable(R.drawable.icon_email);
```
### Supported attribute
- background
- src
- textColor
- tabIndicator
- tabIndicatorColor
### Supported view
- All views which name start with android
- com.google.android.material.tabs.TabLayout
 