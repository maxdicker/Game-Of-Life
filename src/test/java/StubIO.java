import io.IO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StubIO implements IO {
    private List<String> stubInput;
    private int readCount = 0;
    private List<String> stubOutput = new ArrayList<>();

    public StubIO(String stubInput) {
        this.stubInput = new ArrayList<>(Collections.singletonList(stubInput));
    }

    public StubIO(List<String> stubInput) {
        this.stubInput = new ArrayList<>(stubInput);
    }

    public List<String> getOutput() {
        return stubOutput;
    }

    @Override
    public String readUserInput() {
        return stubInput.get(readCount++);
    }

    @Override
    public void displayOutput(String stubOutput) {
        this.stubOutput.add(stubOutput);
    }
}
