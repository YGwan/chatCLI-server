package communication.chatgpt.data;

public enum Completions {

    ENDPOINT("https://api.openai.com/v1/completions"),
    MODEL("text-davinci-003"),
    TWEET_CLASSIFIER("Classify the sentiment in these tweets:\n"),
    TRANSLATE("Translate this into Korean\n");

    private final String data;

    Completions(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
