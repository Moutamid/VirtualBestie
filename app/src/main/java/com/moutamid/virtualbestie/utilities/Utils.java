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

    public static void showOfflineDialog(Context con) {

        Button okayBtn;

        final Dialog dialogOffline = new Dialog(con);
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

    public static void showWorkDoneDialog(Context con) {

        final Dialog dialog = new Dialog(con);
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

    public static void showSuicidalDialog(Context cont) {
        new AlertDialog.Builder(cont)
                .setTitle("Are you suicidal!!?")
                .setMessage("Please contact on any of the following details to help you out!\n\nContact info: +1 (xxx) xxx-xxxx")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    public static void showDialog(Context cont,String message, DialogInterface.OnClickListener positiveListener) {
        new AlertDialog.Builder(cont)
                .setTitle("Result!")
                .setMessage(message)
                .setPositiveButton("OK", positiveListener)
                .show();
    }

    public static void showTriviaHelpDialog(Context contex) {

        new AlertDialog.Builder(contex)
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


        quizzesList.add(new Quiz("1. Seahorses have stomachs for the absorption of nutrients from food. ", false));
        quizzesList.add(new Quiz("2. The letter H is between letters G and J on a keyboard", true));
        quizzesList.add(new Quiz("3. Camels have three sets of eyelashes", true));
        quizzesList.add(new Quiz("4. There are five zeros in one hundred thousand", true));
        quizzesList.add(new Quiz("5. There are stars and zigzags on the flag of America", false));
        quizzesList.add(new Quiz("6. If you add the two numbers on the opposite sides of dice together, the answer is always 7", true));
        quizzesList.add(new Quiz("7. New York is nicknamed 'The Big Orange'", false));
        quizzesList.add(new Quiz("8. The human body is full of friendly bacteria, with organisms on our skin and in our guts helping keep our bodies humming.", true));
        quizzesList.add(new Quiz("9. Scallops don't have good vision", false));
        quizzesList.add(new Quiz("10. The dinosaur with the longest neck for its body size is Mamenchisaurus hochuanensis, a sauropod dino that lived in what is now China.", true));
        quizzesList.add(new Quiz("11. The deepest part of the ocean is about 36,200 feet down (11,030 m) which is more like 25 Empire State Buildings", true));
        quizzesList.add(new Quiz("12. Your hand has a built in snuff box", true));
        quizzesList.add(new Quiz("13. The moon is just 50 percent of the mass of Earth.", false));
        quizzesList.add(new Quiz("14. Toilet paper is a relatively recent invention, but the ancients still had to wipe. Roman philosopher Seneca, who lived from 4 B.C. to A.D. 65, recorded the use of a sponge attached to a stick that did the job.", true));
        quizzesList.add(new Quiz("15. Nearly three percent of the ice in Antarctic glaciers is penguin urine. ", true));
        quizzesList.add(new Quiz("16. Apes cant laugh.", false));
        quizzesList.add(new Quiz("17. A snail can sleep for three months.", false));
        quizzesList.add(new Quiz("18. Your nose and sinuses produce almost one liter of mucus a day (which you swallow.)", true));
        quizzesList.add(new Quiz("19. Tasting is never determined by what you’re smelling", false));
        quizzesList.add(new Quiz("20. The hair on our heads is vellus hair, along with facial and chest hair in men and pubic and armpit hair in both genders.", false));
        quizzesList.add(new Quiz("21. It takes a sloth two weeks to digest its food.", true));
        quizzesList.add(new Quiz("22. In japan, instead of a man in the moon, people see a rabbit in the moon.", true));
        quizzesList.add(new Quiz("23. The quills on African porcupines are as long as three pencils.", true));
        quizzesList.add(new Quiz("24. A cow gives nearly 200,000 glasses of milk in a lifetime.", true));
        quizzesList.add(new Quiz("25. You can sneeze during sleep", false));
        quizzesList.add(new Quiz("26. The bent joint in the legs of the flamingo is its ankles.", true));
        quizzesList.add(new Quiz("27. There is a world championship for throwing mobile phones. ", true));
        quizzesList.add(new Quiz("28. During the Apollo 17 mission, Harrison Schmitt suffered from a severe allergic reaction to the dust found on the surface of the moon.", true));
        quizzesList.add(new Quiz("29. Watching horror movies has no reaction to body", false));
        quizzesList.add(new Quiz("30. Eleanor Roosevelt once snuck out of a white house event to go on a joyride in a plane.", true));
        quizzesList.add(new Quiz("31. The pupil of a goat is diagonal.", false));
        quizzesList.add(new Quiz("32. A double rainbow is a mirror image of the first rainbow.", true));
        quizzesList.add(new Quiz("33. Human facial hair grows faster than any other hair on the body.", true));
        quizzesList.add(new Quiz("34. Human eyes contain a small blind spot where the optic nerve passes through the retina. ", true));
        quizzesList.add(new Quiz("35. Mucus contains chemicals that keep you healthy. That’s why when you’re sick, mucus production nearly doubles.", true));
        quizzesList.add(new Quiz("36. Green tea contains antiseptic properties, which can help to keep your gums healthy.", true));
        quizzesList.add(new Quiz("37. The prevailing theory in Harvey’s time was that the lungs, not the heart, moved blood through the body", true));
        quizzesList.add(new Quiz("39. At the height of his popularity, Charlie Chaplin entered a Charlie Chaplin look-a-like competition in San Francisco. He came in 20th place.", true));
        quizzesList.add(new Quiz("40. Bats always turn right when leaving a cave.", false));
        quizzesList.add(new Quiz("41. A group of jellyfish is not a herd, or a school, or a flock; it's called a smack.", true));
        quizzesList.add(new Quiz("42. You get a new top layer of skin (epidermis) every 7 days.", false));
        quizzesList.add(new Quiz("43. It is possible to hypnotize a frog by placing it on its back and gently stroking its stomach. ", true));
        quizzesList.add(new Quiz("44. A rhinoceros' horn is made of hair.", true));
        quizzesList.add(new Quiz("45. Slugs have six noses", false));
        quizzesList.add(new Quiz("46. The heart of a shrimp is located in its head.", true));
        quizzesList.add(new Quiz("47. Around 50 percent of orangutans have fractured bones, due to falling out of trees on a regular basis. ", true));
        quizzesList.add(new Quiz("48. Frogs cannot vomit. If one absolutely has to, then it will vomit its entire stomach. ", true));
        quizzesList.add(new Quiz("49. Dead skin comprises about a billion tons of dust in the earth’s atmosphere.", true));
        quizzesList.add(new Quiz("50. New Jersey has the highest concentration of shopping malls.", true));
        quizzesList.add(new Quiz("51. Humans have 12 million olfactory (smell) receptor cells, while rabbits have 100 million and bloodhounds have four billion.", true));
        quizzesList.add(new Quiz("52. Broome could make Harry Potter invisible for a day", false));
        quizzesList.add(new Quiz("53. On average, we lose 50 to 100 strands of hair a day from the scalp.", true));
        quizzesList.add(new Quiz("54. Only a few mammals are regarded as being pretty much hairless, these include elephants, rhinoceroses, hippopotamuses, walruses, pigs, whales and naked mole rats.", true));
        quizzesList.add(new Quiz("55. Bananas grow upside down", true));
        quizzesList.add(new Quiz("56. Queen Elizabeth the 1st was the daughter of King Henry the 8th of England", true));
        quizzesList.add(new Quiz("57. Vitamin C is also known by the chemical name of Ascorbic Acid", true));
        quizzesList.add(new Quiz("58. Camels have three sets of eyelids", true));
        quizzesList.add(new Quiz("59. Australia the largest producer of wool in the world", true));
        quizzesList.add(new Quiz("60. China the largest country in the world", false));
        quizzesList.add(new Quiz("61. Zeus is known as the king of Gods", true));
        quizzesList.add(new Quiz("62. A slug has four noses in total", true));
        quizzesList.add(new Quiz("63. There is no railway system in Iceland", true));
        quizzesList.add(new Quiz("64. Humans have 4 senses", false));
        quizzesList.add(new Quiz("65. Archimedes is considered as the father of History", false));
        quizzesList.add(new Quiz("66. A group of crows is called a ‘murder’", true));
        quizzesList.add(new Quiz("67. The full form of CVD is cardiovascular disease", true));
        quizzesList.add(new Quiz("68. An increase in RBCs has nothing to do with the onset of anaemia", true));
        quizzesList.add(new Quiz("69. Robert Brown discovered the cell nucleus", true));
        quizzesList.add(new Quiz("70. Stapes is the smallest bone in the human body", true));
        quizzesList.add(new Quiz("71. Canada has the most lakes in the world", true));
        quizzesList.add(new Quiz("72. Bolivia is the world’s flattest country", true));
        quizzesList.add(new Quiz("73. The world’s oldest country San Marino is in Asia", false));
        quizzesList.add(new Quiz("74. Brazil and France share a 673-kilometre border", true));
        quizzesList.add(new Quiz("75. Cheating on an exam in Bangladesh is punishable by imprisonment", true));
        quizzesList.add(new Quiz("76. It snows in the Sahara Desert", true));
        quizzesList.add(new Quiz("77. There are only two countries in the world where Coca Cola does not exist", true));
        quizzesList.add(new Quiz("78. Sudan has the most pyramids in the world", true));
        quizzesList.add(new Quiz("79. Colombia’s brightest rainbow is in its river", true));
        quizzesList.add(new Quiz("80. The Sahara Desert used to be a tropical rainforest in the past", true));
        quizzesList.add(new Quiz("81. Macchu Pichu is an earthquake-proof city", true));
        quizzesList.add(new Quiz("82. You could walk from Russia to Alaska", true));
        quizzesList.add(new Quiz("83. China has two timezones", false));
        quizzesList.add(new Quiz("84. San Fransisco’s Golden Gate Bridge ‘speaks’", true));
        quizzesList.add(new Quiz("85. Between China and Alaska lies the Bering Strait. ", false));
        quizzesList.add(new Quiz("86. Yala National Park is located in Kenya and famous for safari", false));
        quizzesList.add(new Quiz("87. Taiwan has a festival to appreciate their monkeys", false));
        quizzesList.add(new Quiz("88. You’re always seven years behind in Thailand", false));
        quizzesList.add(new Quiz("89. France is the most visited country with 89 million annual tourists", true));
        quizzesList.add(new Quiz("90. Sai Yok National Park in Japan has the world’s tiniest bats", false));
        quizzesList.add(new Quiz("91. In Rwanda, you can order Macs on skis", false));
        quizzesList.add(new Quiz("92. There’s a village in the Netherlands with no streets, only canals", true));
        quizzesList.add(new Quiz("93. There’s a rock in Australia that’s bigger than Ayer’s Rock Uluru", true));
        quizzesList.add(new Quiz("94. New Carolina has the steepest residential area in the world", false));
        quizzesList.add(new Quiz("95. In Texas, you can experience sunny beaches and snowy mountains on the same island", false));
        quizzesList.add(new Quiz("96. The quills on porcupines in Africa are as long as 3 pencils together.", true));
        quizzesList.add(new Quiz("97. Throwing phones is a world championship phenomenon", true));
        quizzesList.add(new Quiz("98. There is a blind spot present in human eyes, also the location where the optic nerve mostly glides through the retina", true));
        quizzesList.add(new Quiz("99. The outer region of the nose is called the pinna", false));
        quizzesList.add(new Quiz("100. Anything that has a certain mass and occupies space is called an atom", false));

        //----------------------------------------------------------------------------------------
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


