package dev.moutamid.chatty.chatbot;

import static dev.moutamid.chatty.utilities.Utils.getArrayList;
import static dev.moutamid.chatty.utilities.Utils.store;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

import de.hdodenhof.circleimageview.CircleImageView;
import dev.moutamid.chatty.R;
import dev.moutamid.chatty.helper.BrowserActivity;
import dev.moutamid.chatty.helper.Helper;
import dev.moutamid.chatty.helper.translate_api;
import dev.moutamid.chatty.models.ResponsesModel;
import dev.moutamid.chatty.utilities.Constants;
import dev.moutamid.chatty.utilities.Utils;

public class ChattyViewerActivity extends AppCompatActivity {// implements AIListener {
    private static final String TAG = "ChattyViewerActivity";
    private Context context = ChattyViewerActivity.this;

    private RecyclerView recyclerView;
    private RecyclerViewAdapterMessages adapter;
    private LinearLayoutManager linearLayoutManager;
    private Handler handler;

    private Button tabBtn;

    private ImageView fab_img;

    private CircleImageView myMsgStatusImg;

    private EditText editText;

    private RelativeLayout addBtn;
    private ScrollView editTextLayout;

    private RequestQueue mRequestQueue;
    private Bitmap imgBoy;

    ArrayList<ChatMessage> chatMessageArrayList =
            getArrayList(Constants.CHAT_BOT_MESSAGES, ChatMessage.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatty_viewer);

//        showAskDIalog();

        Log.d(TAG, "onCreate: msgUser: " + chatMessageArrayList.toString());
        editText = findViewById(R.id.editText);
        addBtn = findViewById(R.id.addBtn);
        editTextLayout = findViewById(R.id.edittextLayout);
        myMsgStatusImg = findViewById(R.id.myMessageStatus);
        fab_img = findViewById(R.id.fab_img);

        imgBoy = BitmapFactory.decodeResource(getResources(), R.drawable.boy);

        tabBtn = findViewById(R.id.tabBtn);

        handler = new Handler();

        String msgStatus = Utils.getString("msgStatus", "Error");

        if (msgStatus.equals("true")) {
            Log.d(TAG, "onCreate: if (msgStatus.equals(\"true\")) {");
            myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.boy));
        }

        // below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(ChattyViewerActivity.this);
        mRequestQueue.getCache().clear();

        final Bitmap sendImg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_send_white_24dp);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");

                String message = editText.getText().toString().trim();
                Log.d(TAG, "onClick: message " + message);
                Helper.ImageViewAnimatedChange(ChattyViewerActivity.this, fab_img, sendImg);

                if (!message.equals("") && Helper.isOnline() && !TextUtils.isEmpty(message)) {
                    Log.d(TAG, "onClick: if (!message.equals(\"\") && Helper.isOnline() && !TextUtils.isEmpty(message)) {");

                    chatMessageArrayList.add(new ChatMessage(message, Constants.USER_MESSAGE));
                    initRecyclerView();
                    Log.i(TAG, "onClick: started: " + message);
                    setMyMsgStatusImg(message);

                    editText.setText("");

                    addBtn.setEnabled(false);
                    /*handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "run: ");
                            addBtn.setEnabled(true);
                        }
                    }, 3100);*/


                } else if (message.equals("")) {

                    editText.setError("Please add a message!");
                    editText.requestFocus();

                    //aiService.startListening();
                } else if (!Helper.isOnline()) {
                    Toast.makeText(ChattyViewerActivity.this, "You are not online!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private class ConvertRomanToReal extends AsyncTask<Void, Void, Void> {

        String editTextStr;
        String value;
        String outputSentence;
        String inputSentence;
        String englishSentence;
        String urduSentence;

        public ConvertRomanToReal(String inputSentence) {
            this.inputSentence = inputSentence;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            editTextStr = inputSentence;

//            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            outputSentence = "";

            StringTokenizer t = new StringTokenizer(editTextStr);
            String word = "";

            while (t.hasMoreTokens()) {
                word = t.nextToken();

                outputSentence += " " + getTransliteration(word);

            }
            Log.i(TAG, "doInBackground: ouputSentence: " + outputSentence);
            translateUrduToEnglish();

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.i(TAG, "onPostExecute: passedToBot");

//            queryBot();

        }

        private void queryBot() {
            // url for our brain
            // make sure to add mshape for uid.
            // make sure to add your url.
            String url = "http://api.brainshop.ai/get?bid=160533&key=HRVPYcwch6xZXykp&uid=[mshape]&msg=" + englishSentence;
//                String url = "http://api.brainshop.ai/get?bid=160533&key=HRVPYcwch6xZXykp&uid=[uid]&msg=[msg]" + message;

            // creating a variable for our request queue.
            RequestQueue queue = Volley.newRequestQueue(ChattyViewerActivity.this);

            // on below line we are making a json object request for a get request and passing our url .
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {

                        // in on response method we are extracting data
                        // from json response and adding this response to our array list.
                        String botResponse = response.getString("cnt");
                        Log.i(TAG, "onResponse: gotBotResponse: " + botResponse);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                translateEnglishToUrdu(botResponse);
                            }
                        }).start();

                    } catch (JSONException e) {
                        e.printStackTrace();

                        chatMessageArrayList.add(new ChatMessage("I'm busy", Constants.BOT_MESSAGE));
                        Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

                        initRecyclerView();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    chatMessageArrayList.add(new ChatMessage("I'm busy", Constants.BOT_MESSAGE));
                    Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

                    initRecyclerView();

                }
            });

            // at last adding json object
            // request to our queue.
            queue.add(jsonObjectRequest);

            Utils.store("msgStatus", "true");

        }

        private void translateEnglishToUrdu(String sentence) {
            //https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ur&dt=t&q=morning

            /*try {
                String encode = URLEncoder.encode(sentence.trim(), "utf-8");
                StringBuilder sb = new StringBuilder();
                sb.append("https://translate.googleapis.com/translate_a/single?client=gtx&sl=");
                sb.append("en");
                sb.append("&tl=");
                sb.append("ur");
                sb.append("&dt=t&q=");
                sb.append(encode);

                URL google = null;
                google = new URL(sb.toString());
                BufferedReader in = null;
                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));
                String input = null;
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if ((input = in != null ? in.readLine() : null) == null) break;
                    }
                    stringBuffer.append(input);
                }
                if (in != null) {
                    in.close();
                }

                JSONArray jSONArray = new JSONArray(stringBuffer.toString()).getJSONArray(0);
                String str2 = "";
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append(jSONArray2.get(0).toString());
                    str2 = sb2.toString();
                }
                urduSentence = str2;

            } catch (Exception e) {
                Log.e("translate_api", e.getMessage());
                Log.e("translate_api", e.getStackTrace().toString());
                Log.e("translate_api", e.toString());
                e.printStackTrace();

//                urduSentence = sentence;
                urduSentence = e.getMessage();
            }*/

            translate_api translate = new translate_api();
            translate.setOnTranslationCompleteListener(new translate_api.OnTranslationCompleteListener() {
                @Override
                public void onStartTranslation() {
                    // here you can perform initial work before translated the text like displaying progress bar
                }

                @Override
                public void onCompleted(String text) {
                    // "text" variable will give you the translated text
                    urduSentence = text;
                    Log.i(TAG, "translateEnglishToUrdu: urduSentence: " + urduSentence);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tabBtn.setText("Online");
                            Log.i(TAG, "run: addedToChat");
                            chatMessageArrayList.add(new ChatMessage(urduSentence, Constants.BOT_MESSAGE));

                            Log.d(TAG, "onPostExecute: reply: " + urduSentence);
                            Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

                            initRecyclerView();

                            store(Constants.CHAT_BOT_MESSAGES, chatMessageArrayList);

                            store("chattyLastMsg", urduSentence);

                            addBtn.setEnabled(true);
                        }
                    });
                }

                @Override
                public void onError(Exception e) {

                }
            });
            translate.execute(sentence, "en", "ur");


//            if (urduSentence.contains("https://www.google.com/sorry/index?continue=https://translate.googleapis")) {
//                finish();
//                startActivity(new Intent(ChattyViewerActivity.this, BrowserActivity.class)
//                        .putExtra(Constants.TEXT_MESSAGES, urduSentence));
//                return;
//            }


        }

        private void translateUrduToEnglish() {
            //https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ur&dt=t&q=morning

            translate_api translate = new translate_api();
            translate.setOnTranslationCompleteListener(new translate_api.OnTranslationCompleteListener() {
                @Override
                public void onStartTranslation() {
                    // here you can perform initial work before translated the text like displaying progress bar
                }

                @Override
                public void onCompleted(String text) {
                    // "text" variable will give you the translated text
                    englishSentence = text;
                    Log.i(TAG, "translateUrduToEnglish: englishSentence: " + englishSentence);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            queryBot();
                        }
                    });
                }

                @Override
                public void onError(Exception e) {

                }
            });
            translate.execute(outputSentence, "ur", "en");

            /*try {
                String encode = URLEncoder.encode(outputSentence.trim(), "utf-8");
                StringBuilder sb = new StringBuilder();
                sb.append("https://translate.googleapis.com/translate_a/single?client=gtx&sl=");
                sb.append("ur");
                sb.append("&tl=");
                sb.append("en");
                sb.append("&dt=t&q=");
                sb.append(encode);

                URL google = null;
                google = new URL(sb.toString());
                BufferedReader in = null;
                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));
                String input = null;
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if ((input = in != null ? in.readLine() : null) == null) break;
                    }
                    stringBuffer.append(input);
                }
                if (in != null) {
                    in.close();
                }

                JSONArray jSONArray = new JSONArray(stringBuffer.toString()).getJSONArray(0);
                String str2 = "";
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append(jSONArray2.get(0).toString());
                    str2 = sb2.toString();
                }
                englishSentence = str2;

                *//*HttpResponse execute = new DefaultHttpClient().execute(new HttpGet(sb.toString()));
                StatusLine statusLine = execute.getStatusLine();
                if (statusLine.getStatusCode() == 200) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    execute.getEntity().writeTo(byteArrayOutputStream);
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    byteArrayOutputStream.close();
                    JSONArray jSONArray = new JSONArray(byteArrayOutputStream2).getJSONArray(0);
                    String str2 = "";
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str2);
                        sb2.append(jSONArray2.get(0).toString());
                        str2 = sb2.toString();
                    }
                    englishSentence = str2 ;
                }
                execute.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());*//*
            } catch (Exception e) {
                Log.e("translate_api", e.getMessage());
//                listener.onError(e);
                englishSentence = e.getMessage();
//                englishSentence = outputSentence;
            }
            if (englishSentence.contains("https://www.google.com/sorry/index?continue=https://translate.googleapis")) {
                finish();
                startActivity(new Intent(ChattyViewerActivity.this, BrowserActivity.class)
                        .putExtra(Constants.TEXT_MESSAGES, englishSentence));
            }*/
        }

        private String getTransliteration(String pWord) {
            String link1 = "https://www.google.com/transliterate/indic?tlqt=1&langpair=en|ur&text=";
            String link2 = "https://www.google.com/transliterate/indic?tlqt=1&langpair=ur|en&text=";

            value = getHtmlString(link1, pWord);

            if (value.equals("null"))
                value = getHtmlString(link2, pWord);

            JSONArray jsonArray1 = null;

            try {
                jsonArray1 = new JSONArray(value);

                JSONObject jsonObject = jsonArray1.getJSONObject(0);

                JSONArray jsonArray = jsonObject.getJSONArray("hws");

                value = jsonArray.getString(0);//jsonArray.length()-1

            } catch (JSONException e) {
                value = pWord;
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TesterActivity.this, value + " VALUE IS NULL: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });*/
                Log.e(TAG, "run: " + e.getMessage());
            }

            return value;
        }

        private String getHtmlString(String uurl, String uWord) {
            URL google = null;
            String htmlData = uWord;

            String url = uurl + uWord;

            try {

                google = new URL(url);

                BufferedReader in = null;

                in = new BufferedReader(new InputStreamReader(google != null ? google.openStream() : null));

                String input = null;
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if ((input = in != null ? in.readLine() : null) == null) break;
                    }

                    stringBuffer.append(input);
                }

                if (in != null) {
                    in.close();
                }

                htmlData = stringBuffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return htmlData;
        }

    }

    private void showAskDIalog() {
        new AlertDialog.Builder(ChattyViewerActivity.this)
                .setTitle("Please select!")
                .setMessage("Which Type of chatbot you are trying to chat with?")
                .setPositiveButton("Editable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editable = true;
                    }
                })
                .setNegativeButton("Non-editable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editable = false;
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ");
        recyclerView = findViewById(R.id.recyclerViewMessages);
        adapter = new RecyclerViewAdapterMessages();
        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        scrollRecyclerViewToEnd();

    }

    private void scrollRecyclerViewToEnd() {
        Log.d(TAG, "scrollRecyclerViewToEnd: ");
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                int msgCount = adapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if (lastVisiblePosition == -1 ||
                        (positionStart >= (msgCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

        initRecyclerView();

    }

    private void setMyMsgStatusImg(String message) {
        Log.d(TAG, "setMyMsgStatusImg: ");
        myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_msg_not_sent));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_msg_sent));
                setDeliveredImage(message);
            }
        }, 1000);

    }

    private void setDeliveredImage(String message) {
        Log.d(TAG, "setDeliveredImage: ");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_msg_delivered));
                setSeenImage(message);
            }
        }, 1000);

    }

    boolean editable = false;

    private void setSeenImage(String message) {
        Log.d(TAG, "setSeenImage: ");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabBtn.setText("Typing...");

                myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.boy));

                if (editable) {
                    retrieveAnswerFromBrain(message.toLowerCase());
                    return;
                }

                new ConvertRomanToReal(message).execute();

            }
        }, 1000);

    }

    private void retrieveAnswerFromBrain(String query) {

        ArrayList<ResponsesModel> brain = ChatBrain.asList();

        String botResponse = "I don't understand!";

        for (ResponsesModel model : brain) {

            if (model.getQuestion().toLowerCase().equals(query))
                botResponse = model.getAnswer();

        }

        tabBtn.setText("Online");

        chatMessageArrayList.add(new ChatMessage(botResponse, Constants.BOT_MESSAGE));

        Log.d(TAG, "retrieveAnswerFromBrain: reply: " + botResponse);

        Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

        initRecyclerView();

        store(Constants.CHAT_BOT_MESSAGES, chatMessageArrayList);
        store("chattyLastMsg", botResponse);
    }

    private class RecyclerViewAdapterMessages extends RecyclerView.Adapter
            <RecyclerViewAdapterMessages.ViewHolderMessages> {

        @NonNull
        @Override
        public ViewHolderMessages onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msglist, parent, false);
            Log.d(TAG, "onCreateViewHolder: ");
            ViewHolderMessages holderMessages = new ViewHolderMessages(view);
            return holderMessages;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderMessages holder, int position) {
            Log.d(TAG, "onBindViewHolder: position: " + position);

            if (chatMessageArrayList.get(position).getMsgUser().equals(Constants.USER_MESSAGE)) {
                Log.d(TAG, "onBindViewHolder: if (msgUser.get(position).equals(\"user\")) {");

                holder.rightText.setText(chatMessageArrayList.get(position).getMsgText());

                holder.rightText.setVisibility(View.VISIBLE);
                holder.leftText.setVisibility(View.GONE);

            } else {
                Log.d(TAG, "onBindViewHolder: } else {");

                holder.leftText.setText(chatMessageArrayList.get(position).getMsgText());

                holder.rightText.setVisibility(View.GONE);
                holder.leftText.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return chatMessageArrayList.size();
        }

        public class ViewHolderMessages extends RecyclerView.ViewHolder {

            TextView leftText, rightText;
            LinearLayout rightTextLayout;

            public ViewHolderMessages(@NonNull View v) {
                super(v);

                leftText = (TextView) v.findViewById(R.id.leftText);
                rightText = (TextView) v.findViewById(R.id.rightText);
                rightTextLayout = v.findViewById(R.id.rightTextLayout);
            }
        }

    }

}
