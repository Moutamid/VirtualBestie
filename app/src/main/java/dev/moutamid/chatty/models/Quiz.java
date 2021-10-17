package dev.moutamid.chatty.models;

public class Quiz {
    private String question;
    private boolean answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Quiz() {
    }

    public Quiz(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }
}
