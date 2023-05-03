package communication.chatgpt.data;

public enum Chat {
    CHAT_ENDPOINT("https://api.openai.com/v1/chat/completions"),
    MODEL("gpt-3.5-turbo"),
    ROLE("user");

    private final String data;

    Chat(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
