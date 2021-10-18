package dev.moutamid.chatty.chatbot;

import static dev.moutamid.chatty.utilities.Utils.getArrayList;
import static dev.moutamid.chatty.utilities.Utils.store;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.AIService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;*/
import de.hdodenhof.circleimageview.CircleImageView;
import dev.moutamid.chatty.R;
import dev.moutamid.chatty.helper.Helper;
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

//    private SharedPreferences sharedPreferences;

    private ImageView fab_img;

    private CircleImageView myMsgStatusImg;

    private EditText editText;

    private RelativeLayout addBtn;
    private ScrollView editTextLayout;

//    private DatabaseReference ref;

    //    private String message;
    private RequestQueue mRequestQueue;
    private Bitmap imgBoy;

//    private ArrayList<String> msgText = getArrayList(Constants.TEXT_MESSAGES, "Hi");
//    private ArrayList<String> msgUser = getArrayList(Constants.TEXT_USER, "user");

    ArrayList<ChatMessage> chatMessageArrayList =
            getArrayList(Constants.CHAT_BOT_MESSAGES, ChatMessage.class);

    /*private ArrayList<String> msgText = new ArrayList<>();
    private ArrayList<String> msgUser = new ArrayList<>();
*/
//    ArrayList<String> tasksArrayList = getArrayList(Constants.NOTES_LIST);


//    private AIService aiService;
//    private AIDataService aiDataService;
//    private AIRequest aiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatty_viewer);

//        Log.d(TAG, "onCreate: msgText: " + msgText.toString());
//        Log.d(TAG, "onCreate: msgUser: " + msgUser.toString());
        Log.d(TAG, "onCreate: msgUser: " + chatMessageArrayList.toString());
        editText = findViewById(R.id.editText);
        addBtn = findViewById(R.id.addBtn);
        editTextLayout = findViewById(R.id.edittextLayout);
        myMsgStatusImg = findViewById(R.id.myMessageStatus);
        fab_img = findViewById(R.id.fab_img);

        imgBoy = BitmapFactory.decodeResource(getResources(), R.drawable.boy);

        tabBtn = findViewById(R.id.tabBtn);

        handler = new Handler();
//        sharedPreferences = this.getSharedPreferences("moutamid.spdf.com.chatty", Context.MODE_PRIVATE);
        String msgStatus = Utils.getString("msgStatus", "Error");

        // Changing the color of EditText bar
//        if (Build.VERSION.SDK_INT < 21) {
//            editText.setBackgroundColor(getResources().getColor(R.color.lighterGrey));
//            editTextLayout.setBackgroundColor(getResources().getColor(R.color.lighterGrey));
//
//        }

        if (msgStatus.equals("true")) {
            Log.d(TAG, "onCreate: if (msgStatus.equals(\"true\")) {");
            myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.boy));
        }

/*//        String userName = Utils.getString("userName", "Error");

//        ref = FirebaseDatabase.getInstance().getReference().child(userName);
//        ref.keepSynced(true);*/

        // below line is to initialize our request queue.
        mRequestQueue = Volley.newRequestQueue(ChattyViewerActivity.this);
        mRequestQueue.getCache().clear();

        /*final AIConfiguration config = new AIConfiguration("9a656058c9ba4eed9ce60bdb2ad6613b",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiService = AIService.getService(this, config);
        aiService.setListener(this);
        aiDataService = new AIDataService(this, config);
        aiRequest = new AIRequest();*/

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

//                    msgUser.add("user");
//                    msgText.add(message);
                    chatMessageArrayList.add(new ChatMessage(message, Constants.USER_MESSAGE));
                    initRecyclerView();

                    setMyMsgStatusImg(message);

                    editText.setText("");

//                    Utils.store("chattyLastMsg", message);

                    addBtn.setEnabled(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "run: ");
                            addBtn.setEnabled(true);
                        }
                    }, 3100);


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

    /*private void uploadMessageAndGetResponse(String message) {
        Log.d(TAG, "uploadMessageAndGetResponse: ");
        if (!TextUtils.isEmpty(message)) {
            Log.d(TAG, "uploadMessageAndGetResponse: if (!TextUtils.isEmpty(message)) {");

//            aiRequest.setQuery(message);

//            tabBtn.setText("Typing...");

            ChatMessage chatMessage = new ChatMessage(message, "user");
//            msgUser.add("user");
//            msgText.add(message);
//            Utils.store(Constants.TEXT_USER, msgUser);
//            Utils.store(Constants.TEXT_MESSAGES, msgText);
//            initRecyclerView();
            Log.d(TAG, "uploadMessageAndGetResponse: message: " + message);
//            ref.child("chat").push().setValue(chatMessage);

            *//*new AsyncTask<AIRequest, Void, AIResponse>() {

                @Override
                protected AIResponse doInBackground(AIRequest... aiRequests) {
                    final AIRequest request = aiRequests[0];
                    Log.d(TAG, "doInBackground: ");
                    try {
                        Log.d(TAG, "doInBackground: try");
                        final AIResponse response = aiDataService.request(aiRequest);

                        return response;

                    } catch (AIServiceException e) {
                        e.printStackTrace();
                        Log.d("MainActivity", " moutamid.spdf.com.chatty: doInBackground: AIResposne Error!");
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(AIResponse response) {
                    Log.d(TAG, "onPostExecute: ");
                    if (response != null) {
                        Log.d(TAG, "onPostExecute: if (response != null) {");

                        Result result = response.getResult();
                        String reply = result.getFulfillment().getSpeech();

                        tabBtn.setText("Online");

                        msgUser.add("bot");
                        msgText.add(reply);
                        Log.d(TAG, "onPostExecute: reply: " + reply);
                        Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

                        initRecyclerView();

                        ChatMessage chatMessage = new ChatMessage(reply, "bot");
//                        msgUser.add("bot");
//                        msgText.add(reply);
                        store(Constants.TEXT_USER, msgUser);
                        store(Constants.TEXT_MESSAGES, msgText);
//                        ref.child("chat").push().setValue(chatMessage);
                        store("chattyLastMsg", reply);
                    } else Log.d(TAG, "onPostExecute: response is null");
                }
            }.execute(aiRequest);*//*
        } else if (message.equals("")) {
            Log.d(TAG, "uploadMessageAndGetResponse: } else if (message.equals(\"\")) {");

            editText.setError("Please add a message!");
            editText.requestFocus();

        }
    }*/


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

    /*@Override
    public void onResult(ai.api.model.AIResponse response) {
        Log.d(TAG, "onResult: ");
        Result result = response.getResult();

        String message = result.getResolvedQuery();
        ChatMessage chatMessage0 = new ChatMessage(message, "user");
//        ref.child("chat").push().setValue(chatMessage0);

        String reply = result.getFulfillment().getSpeech();

        msgUser.add("bot");
        msgText.add(reply);
        Utils.store(Constants.TEXT_USER, msgUser);
        Utils.store(Constants.TEXT_MESSAGES, msgText);
        initRecyclerView();
        Log.d(TAG, "onResult: reply: " + reply);

        ChatMessage chatMessage = new ChatMessage(reply, "bot");
//        ref.child("chat").push().setValue(chatMessage);

        Toast.makeText(this, "On Result Executed!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(ai.api.model.AIError error) {
    }

    @Override
    public void onAudioLevel(float level) {
    }

    @Override
    public void onListeningStarted() {
    }

    @Override
    public void onListeningCanceled() {
    }

    @Override
    public void onListeningFinished() {
    }
*/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
/*//        ref.child("chat").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                msgUser.clear();
//                msgText.clear();
//
//                for (DataSnapshot questions : dataSnapshot.getChildren()) {
//
//                    String message = questions.child("msgText").getValue(String.class);
//                    String user = questions.child("msgUser").getValue(String.class);
//
//                    msgUser.add(user);
//                    msgText.add(message);
//
//                }*/

        initRecyclerView();
/*//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Log.i("Database Error", databaseError.getMessage());
//                Log.i("Database Error", databaseError.getDetails());
//                Log.d("MainActivity", "onCancelled: " + databaseError.getMessage());
//
//            }
//        });*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(ChattyViewerActivity.this, TabbedActivity.class));
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

    private void setSeenImage(String message) {
        Log.d(TAG, "setSeenImage: ");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabBtn.setText("Typing...");

                myMsgStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.boy));
//                uploadMessageAndGetResponse(message);

                // url for our brain
                // make sure to add mshape for uid.
                // make sure to add your url.
                String url = "http://api.brainshop.ai/get?bid=160533&key=HRVPYcwch6xZXykp&uid=[mshape]&msg=" + message;
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
                            tabBtn.setText("Online");

//                            msgUser.add("bot");
//                            msgText.add(botResponse);
//                            /
                            chatMessageArrayList.add(new ChatMessage(botResponse, Constants.BOT_MESSAGE));

                            Log.d(TAG, "onPostExecute: reply: " + botResponse);
                            Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

                            initRecyclerView();

//                            ChatMessage chatMessage = new ChatMessage(reply, "bot");
//                        msgUser.add("bot");
//                        msgText.add(reply);
                            /*store(Constants.TEXT_USER, msgUser);
                            store(Constants.TEXT_MESSAGES, msgText);*/
                            store(Constants.CHAT_BOT_MESSAGES, chatMessageArrayList);
//                        ref.child("chat").push().setValue(chatMessage);
                            store("chattyLastMsg", botResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();

                            // handling error response from bot.
//                            messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
//                            messageRVAdapter.notifyDataSetChanged();

                            /*msgUser.add("bot");
                            msgText.add("No response");*/
                            chatMessageArrayList.add(new ChatMessage("I'm busy", Constants.BOT_MESSAGE));
//                            Log.d(TAG, "onPostExecute: reply: " + botResponse);
                            Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

                            initRecyclerView();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        /*msgUser.add("bot");
                        msgText.add("Sorry no response found: " + error.toString());*/
                        chatMessageArrayList.add(new ChatMessage("I'm busy", Constants.BOT_MESSAGE));

//                        Utils.toast(error.toString());
//                        tabBtn.setText(error.toString());
//                            Log.d(TAG, "onPostExecute: reply: " + botResponse);
                        Helper.CircleImageViewAnimatedChange(ChattyViewerActivity.this, myMsgStatusImg, imgBoy);

//                        adapter.notifyDataSetChanged();

                        initRecyclerView();

                        // error handling.
//                        messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
//                        Toast.makeText(ChattyViewerActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
                    }
                });

                // at last adding json object
                // request to our queue.
                queue.add(jsonObjectRequest);

                Utils.store("msgStatus", "true");
            }
        }, 1000);

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
            //holder.receivedImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.boy));

            if (chatMessageArrayList.get(position).getMsgUser().equals(Constants.USER_MESSAGE)) {
//            if (msgUser.get(position).equals("user")) {
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
//            return msgUser.size();
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
