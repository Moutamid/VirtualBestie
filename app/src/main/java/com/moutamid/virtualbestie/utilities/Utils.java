package com.moutamid.virtualbestie.utilities;


import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.moutamid.virtualbestie.R;
import com.moutamid.virtualbestie.models.Quiz;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static Utils utils;
    private static Context instance;
    private SharedPreferences sp;

    public Utils() {
    }

    public static void clickSound() {
        final MediaPlayer mp = MediaPlayer.create(instance, R.raw.click);
        mp.start();
    }

    public static void explodeSound() {
        final MediaPlayer mp = MediaPlayer.create(instance, R.raw.explode);
        mp.start();
    }

    public static void init(Context context) {
        utils = new Utils();
        instance = context;
        if (utils.sp == null) {
            utils.sp = PreferenceManager.getDefaultSharedPreferences(context);
        }

    }

    private static void checkfornull() {
        if (utils == null)
            throw new NullPointerException("Call init() method in application context class for the manifest");
    }

    // toast
    public static void toast(String msg) {
        checkfornull();
        Toast.makeText(instance, msg, Toast.LENGTH_SHORT).show();
    }

    //putString
    public static void store(String key, String value) {
        checkfornull();
        try {
            utils.sp.edit().putString(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putInt
    public static void store(String key, int value) {
        checkfornull();
        try {
            utils.sp.edit().putInt(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putLong
    public static void store(String key, long value) {
        checkfornull();
        try {
            utils.sp.edit().putLong(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putFloat
    public static void store(String key, float value) {
        checkfornull();
        try {
            utils.sp.edit().putFloat(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putBoolean
    public static void store(String key, boolean value) {
        checkfornull();
        try {
            utils.sp.edit().putBoolean(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //putObject or arrayList
//    public static void store(String key, Object value) {
//          implementation 'com.google.code.gson:gson:2.8.7'
//        checkfornull();
//        try {
//            Gson gson = new GsonBuilder().create();
//            utils.sp.edit().putString(key, gson.toJson(value).toString()).apply();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    //getString
    public static String getString(String key, String defaultvalue) {
        checkfornull();
        try {
            return utils.sp.getString(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    public static String getString(String key) {
        return getString(key, "Error");
    }

    //getInt
    public static int getInt(String key, int defaultvalue) {
        checkfornull();
        try {
            return utils.sp.getInt(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    //getLong
    public static long getLong(String key, long defaultvalue) {
        checkfornull();
        try {
            return utils.sp.getLong(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    public static long getLong(String key) {
        return getLong(key, (long) 0);
    }

    //getFloat
    public static float getFloat(String key, float defaultvalue) {
        checkfornull();
        try {
            return utils.sp.getFloat(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    public static float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    //getBoolean
    public static boolean getBoolean(String key, boolean defaultvalue) {
        checkfornull();
        try {
            return utils.sp.getBoolean(key, defaultvalue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultvalue;
        }
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    //getObject
//    public static <T> Object getObject(String key, Class<?> tClass) {
//          implementation 'com.google.code.gson:gson:2.8.7'
//        checkfornull();
//        try {
//            Gson gson = new GsonBuilder().create();
//            return gson.fromJson(utils.sp.getString(key, ""), tClass);
//        } catch (Exception e) {
//            Log.e("gson", e.getMessage());
//            return "";
//        }
//    }

    //getArrayList
//    public static <T> ArrayList<T> getArrayList(String key, Class<?> tClass) {
//          implementation 'com.google.code.gson:gson:2.8.7'
//        Log.e("_+_++__+_+", "" + tClass.getName());
//        Gson gson = new Gson();
//        String data = utils.sp.getString(key, "");
//        if (!data.trim().isEmpty()) {
//            Type type = new GenericType(tClass);
//            return (ArrayList<T>) gson.fromJson(data, type);
//        }
//        return new ArrayList<T>();
//    }

    //clear single value
    public static void remove(String key) {
        checkfornull();
        try {
            utils.sp.edit().remove(key).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //clear all preference
    public static void removeSharedPref() {
        checkfornull();
        try {
            utils.sp.edit().clear().apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void store(String name, ArrayList<String> arrayList) {
//        Set<String> set = new HashSet<>(arrayList);
//        utils.sp.edit().putStringSet(name, set).apply();
//    }
//
//    public static ArrayList<String> getArrayList(String name) {
//        Set<String> defaultSet = new HashSet<>();
//        defaultSet.add("Error");
//        Set<String> set = utils.sp.getStringSet(name, defaultSet);
//        return new ArrayList<>(set);
//    }

    public String getRandomNmbr(int length) {
        return String.valueOf(new Random().nextInt(length) + 1);
    }

    public static void showOfflineDialog() {

        Button okayBtn;

        final Dialog dialogOffline = new Dialog(instance);
        dialogOffline.setContentView(R.layout.dialog_offline);

        okayBtn = dialogOffline.findViewById(R.id.okay_btn_offline_dialog);
//        TextView titleTv = dialogOffline.findViewById(R.id.title_offline_dialog);
//        TextView descTv = dialogOffline.findViewById(R.id.desc_offline_dialog);
//
//        if (!TextUtils.isEmpty(title))
//            titleTv.setText(title);
//
//        if (!TextUtils.isEmpty(desc))
//            descTv.setText(desc);

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOffline.dismiss();
            }
        });

        dialogOffline.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogOffline.show();

    }

    public static void showWorkDoneDialog() {

        final Dialog dialog = new Dialog(instance);
        dialog.setContentView(R.layout.dialog_work_done);

        Button okayBtn = dialog.findViewById(R.id.okay_btn_work_done_dialog);
//        TextView titleTv = dialog.findViewById(R.id.title_work_done_dialog);
//        TextView descTv = dialog.findViewById(R.id.desc_work_done_dialog);
//
//        if (!TextUtils.isEmpty(title))
//            titleTv.setText(title);
//
//        if (!TextUtils.isEmpty(desc))
//            descTv.setText(desc);

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    public static void showDialog(String message, DialogInterface.OnClickListener positiveListener) {
        new AlertDialog.Builder(instance)
                .setTitle("Result!")
                .setMessage(message)
                .setPositiveButton("OK", positiveListener)
                .show();
    }

    public static void showDialog1() {
        new AlertDialog.Builder(instance)
                .setTitle("How to play the game?")
                .setMessage("You need to swipe Right if the answer is True and swipe Left if the answer False!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

//    public void saveArrayList(ArrayList<String> list, String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
    //      SharedPreferences.Editor editor = prefs.edit();
    //    Gson gson = new Gson();
    //  String json = gson.toJson(list);
    // editor.putString(key, json);
    //editor.apply();     // This line is IMPORTANT !!!
    // }

    //public ArrayList<String> getArrayList(String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
    //      Gson gson = new Gson();
    //    String json = prefs.getString(key, null);
    //  Type type = new TypeToken<ArrayList<String>>() {}.getType();
    //return gson.fromJson(json, type);
    // }

    public String getDate() {

        try {

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";

    }


//    public String getDate(Context context) {
//
//        try {
//
//            Date date = SecureTimer.with(context).getCurrentDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            return sdf.format(date);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "Error";
//
//    }
//
//    public String getNextDate(Context context) {
//
//        try {
//            Date date = SecureTimer.with(context).getCurrentDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//            Calendar c = Calendar.getInstance();
//
//            c.setTime(sdf.parse(sdf.format(date)));
//            c.add(Calendar.DATE, 1);
//            return sdf.format(c.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "Error";
//    }
//
//    public String getPreviousDate(Context context) {
//
//        try {
//            Date date = SecureTimer.with(context).getCurrentDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//            Calendar c = Calendar.getInstance();
//
//            c.setTime(sdf.parse(sdf.format(date)));
//            c.add(Calendar.DATE, -1);
//            return sdf.format(c.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "Error";
//    }

//    public void showSnackBar(View view, String msg) {
//        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//    }
/*public void changeStatusBarColor(Activity activity, int id) {

    // Changing the color of status bar
    if (Build.VERSION.SDK_INT >= 21) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(activity.getResources().getColor(id));
    }

    // CHANGE STATUS BAR TO TRANSPARENT
    //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
}

    // PUBLIC METHOD TO GET VIEW FROM ONE ACTIVITY OR FRAGMENT TO ANOTHER
    //-------------------------------------------------------------------
    //public Utils.NonSwipableViewPager getClassRoomViewPager() {
//
    //      // Class to set current item or change any view from any different activity
//
    //      if (null == classRoomViewPager) {
    //        classRoomViewPager = (Utils.NonSwipableViewPager) findViewById(R.id.class_room_viewPager);
    //  }
//
    //      return classRoomViewPager;
    //}

    From fragment to activty:

((YourActivityClassName)getActivity()).yourPublicMethod();
From activity to fragment:

FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
YourFragmentClass fragment = (YourFragmentClass)fm.findFragmentById(R.id.your_fragment_id);
fragment.yourPublicMethod();
If you added fragment via code and used a tag string when you added your fragment, use findFragmentByTag instead:

YourFragmentClass fragment = (YourFragmentClass)fm.findFragmentByTag("yourTag");

    */

    private static class GenericType implements ParameterizedType {

        private Type type;

        GenericType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

        // implement equals method too! (as per javadoc)
    }

    private static final float DENSITY = Resources.getSystem().getDisplayMetrics().density;
    private static final Canvas sCanvas = new Canvas();

    public static int dp2Px(int dp) {
        return Math.round(dp * DENSITY);
    }

    public static Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.clearFocus();
        Bitmap bitmap = createBitmapSafely(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888, 1);
        if (bitmap != null) {
            synchronized (sCanvas) {
                Canvas canvas = sCanvas;
                canvas.setBitmap(bitmap);
                view.draw(canvas);
                canvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    public static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }
    }

    public static ArrayList<Quiz> quizArrayList() {
        ArrayList<Quiz> quizzesList = new ArrayList<>();

        quizzesList.add(new Quiz("The Big Apple is a nickname given to Washington D.C in 1971.", false));
        quizzesList.add(new Quiz("Muddy York is a nickname for New York in the Winter.", false));
        quizzesList.add(new Quiz("Peanuts are not nuts!", true));
        quizzesList.add(new Quiz("People may sneeze or cough while sleeping deeply.", false));
        quizzesList.add(new Quiz("Copyrights depreciate over time.", true));
        quizzesList.add(new Quiz("Emus can’t fly.", true));
        quizzesList.add(new Quiz("Electrons move faster than the speed of light.", false));
        quizzesList.add(new Quiz("There is no snow on Minecraft.", false));
        quizzesList.add(new Quiz("Light travels in a straight line.", true));
        quizzesList.add(new Quiz("The Mona Liza was stolen from the Louvre in 1911.", true));
        quizzesList.add(new Quiz("If you are a type-A personality you are probably effective under stress.", true));

        quizzesList.add(new Quiz("You can change your personality type.", false));

        quizzesList.add(new Quiz("All introverts are shy and socially anxious.", false));

        quizzesList.add(new Quiz("An ISTP personality stands for introverted, sensual, thoughtful, and proactive.", false));

        quizzesList.add(new Quiz("Janet Jackson performed at halftime of Super Bowl LV.", false));

        quizzesList.add(new Quiz("The Los Angeles Lakers won the 2020 NBA Championship.", true));

        quizzesList.add(new Quiz("Hamilton the musical is the first Broadway show ever written about Hamilton.", false));

        quizzesList.add(new Quiz("The film Moneyball is based on a ttrue story.", true));

        quizzesList.add(new Quiz("The percentage of students in the US who have taken loans to get through college is declining. ", false));

        quizzesList.add(new Quiz("The term inflation refers to a general fall in the prices of most products and services.", false));

        quizzesList.add(new Quiz("A credit card and a debit card are the same.", false));

        quizzesList.add(new Quiz("Ethereum is the second-largest cryptocurrency after Bitcoin.", true));

        quizzesList.add(new Quiz("There are tools to help you monitor your competitor’s marketing efforts.", true));

        quizzesList.add(new Quiz("Facebook is not as popular as it used to be, it’s losing its audiences.", false));

        quizzesList.add(new Quiz("KPI stands for Key Performance Indicator.", true));

        quizzesList.add(new Quiz("ASO stands for app store optimization.", true));

        quizzesList.add(new Quiz("A rich snippet is a strategic section on your website that includes a video or infographic.", false));

        quizzesList.add(new Quiz("Imposters syndrome is a mental illness.", false));

        quizzesList.add(new Quiz("Women usually reach the earning-peak of their career when they are younger than men.", true));

        quizzesList.add(new Quiz("Your employer is obligated to give you a raise every two years. ", false));

        quizzesList.add(new Quiz("Almost 30% of Americans are self-employed.", true));

        return quizzesList;

    }

}


