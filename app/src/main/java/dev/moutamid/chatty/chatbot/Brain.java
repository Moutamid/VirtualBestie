package dev.moutamid.chatty.chatbot;

import java.util.ArrayList;

import dev.moutamid.chatty.models.ResponsesModel;

public class Brain {

    public static ArrayList<ResponsesModel> toList(){
        ArrayList<ResponsesModel> responses = new ArrayList<>();

        responses.add(new ResponsesModel("Hi","How are you?"));
        responses.add(new ResponsesModel("I'm fine","How are you?"));
        responses.add(new ResponsesModel("Where do you live?","I live in London"));
        responses.add(new ResponsesModel("Where do you live?","I live in London"));

        return responses;
    }

}
