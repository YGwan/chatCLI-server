package communication.chatgpt.data;

public enum TweetClassifier {

    ENDPOINT("https://api.openai.com/v1/completions"),
    MODEL("text-davinci-003"),
    MESSAGE("Classify the sentiment in these tweets:\n");

    private final String data;

    TweetClassifier(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
