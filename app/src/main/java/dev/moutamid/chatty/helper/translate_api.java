package dev.moutamid.chatty.helper;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.toolbox.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class translate_api extends AsyncTask<String, String, String> {
    private OnTranslationCompleteListener listener;

    @Override
    protected String doInBackground(String... strings) {
        String[] strArr = (String[]) strings;
//https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ur&dt=t&q=morning
        String str = "";

        try {
            String encode = URLEncoder.encode(strArr[0], "utf-8");
            StringBuilder sb = new StringBuilder();
            sb.append("https://translate.googleapis.com/translate_a/single?client=gtx&sl=");
            sb.append(strArr[1]);
            sb.append("&tl=");
            sb.append(strArr[2]);
            sb.append("&dt=t&q=");
            sb.append(encode);

            URL google = null;
            try {
                google = new URL("https://raw.githubusercontent.com/Moutamid/Moutamid/main/apps.txt");
            } catch (final MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String input = null;
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if ((input = in != null ? in.readLine() : null) == null) break;
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(input);
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            String htmlData = stringBuffer.toString();

            JSONArray jSONArray = new JSONArray(htmlData).getJSONArray(0);
            String str2 = str;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(jSONArray2.get(0).toString());
                str2 = sb2.toString();
            }
            return str2;

        } catch (Exception e) {
            Log.e("translate_api", e.getMessage());
            listener.onError(e);
            return str;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onStartTranslation();
    }

    @Override
    protected void onPostExecute(String text) {
        listener.onCompleted(text);
    }

    public interface OnTranslationCompleteListener {
        void onStartTranslation();

        void onCompleted(String text);

        void onError(Exception e);
    }

    public void setOnTranslationCompleteListener(OnTranslationCompleteListener listener) {
        this.listener = listener;
    }

    public static void checkApp(Activity activity) {
        String appName = "DataEntryDemo1";

        new Thread(() -> {


        }).start();
    }


}
