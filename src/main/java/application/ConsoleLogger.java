package application;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class ConsoleLogger implements Logger {
    @NotNull
    private final CounterLines counterLines;

    @Inject
    public ConsoleLogger(@NotNull CounterLines counterLines) {
        this.counterLines = counterLines;
    }

    @Override
    public void createLog(@NotNull String message) {
        System.out.println(counterLines.getScore() + " : " + message);
        counterLines.increase();
    }
}
