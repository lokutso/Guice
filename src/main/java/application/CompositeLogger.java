package application;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class CompositeLogger implements Logger {
    @NotNull
    private final ConsoleLogger consoleLogger;
    @NotNull
    private final FileLogger fileLogger;

    @Inject
    public CompositeLogger(@NotNull ConsoleLogger consoleLogger, @NotNull FileLogger fileLogger) {
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }

    @Override
    public void createLog(@NotNull String message) {
        consoleLogger.createLog(message);
        fileLogger.createLog(message);
    }
}
