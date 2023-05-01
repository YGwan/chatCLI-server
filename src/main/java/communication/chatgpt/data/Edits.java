package communication.chatgpt.data;

public enum Edits {

    ENDPOINT("https://api.openai.com/v1/edits"),
    MODEL("text-davinci-edit-001"),
    INSTRUCTION("Fix the spelling mistakes");

    private final String data;

    Edits(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
