package sandbox.models;

public class ChatRequest {
    private String inputs;

    public ChatRequest() {}
    public ChatRequest(String inputs) {
        this.inputs = inputs;
    }

    public String getInputs() {
        return inputs;
    }
    public void setInputs(String inputs) {
        this.inputs = inputs;
    }
}
