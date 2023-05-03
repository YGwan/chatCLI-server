package communication.chatgpt.data;

public enum Summarize {
    ENDPOINT("https://api.openai.com/v1/completions"),
    MODEL("text-davinci-003");

    private final String data;

    Summarize(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
