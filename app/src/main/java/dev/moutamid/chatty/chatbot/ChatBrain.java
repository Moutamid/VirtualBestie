package dev.moutamid.chatty.chatbot;

import java.util.ArrayList;

import dev.moutamid.chatty.models.ResponsesModel;

public class ChatBrain {

    public static ArrayList<ResponsesModel> asList(){
        ArrayList<ResponsesModel> list = new ArrayList<>();

        list.add(new ResponsesModel("Hi", "How are you?"));
        list.add(new ResponsesModel("I'm fine", "That's good"));
        list.add(new ResponsesModel("What's your age?", "I'm 18"));

        return list;
    }

}
