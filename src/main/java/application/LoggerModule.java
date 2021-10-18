package application;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class LoggerModule extends AbstractModule {
    @Nullable
    private static String tag;
    @NotNull
    private String typeLogger;
    @NotNull
    private static CounterLines counterLines;

    static {
        counterLines = new CounterLines();
    }

    @Inject
    public LoggerModule(@NotNull String[] args) {
        try {
            this.typeLogger = args[0];
            tag = args[1];
        } catch (ArrayIndexOutOfBoundsException exception) {
            this.typeLogger = "console";
        }
    }

    @Override
    protected void configure() {
        switch (typeLogger) {
            case "console"      -> bind(Logger.class).to(ConsoleLogger.class);
            case "file"         -> bind(Logger.class).toProvider(File.class);
            case "composite"    -> bind(Logger.class).toProvider(Composite.class);
            default             -> System.out.println("Unknown type");
        }
        bind(CounterLines.class).toInstance(counterLines);
    }

    public static final class File implements Provider<Logger> {
        @NotNull
        @Override
        public Logger get() {
            return new FileLogger(counterLines, tag);
        }
    }

    public static final class Composite implements Provider<Logger> {
        @NotNull
        @Override
        public Logger get() {
            return new CompositeLogger(new ConsoleLogger(counterLines), new FileLogger(counterLines, tag));
        }
    }
}
