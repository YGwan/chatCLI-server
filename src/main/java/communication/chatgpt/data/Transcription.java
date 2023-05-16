package communication.chatgpt.data;

public enum Transcription {
    ENDPOINT("https://api.openai.com/v1/audio/transcriptions"),
    MODEL("whisper-1");

    private final String data;

    Transcription(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
