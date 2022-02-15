package hu.futureofmedia.task.contactsapi.entities;

public enum State {

    ACTIVE("active"),
    DELETED("deleted");

    String text;

    State(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
