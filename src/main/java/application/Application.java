package application;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    @NotNull
    private final Logger logger;

    @Inject
    public Application(@NotNull Logger logger) {
        this.logger = logger;
    }

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new LoggerModule(args));
        injector.getInstance(Application.class).waitForInput();
    }

    public void waitForInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                logger.createLog(scanner.nextLine());
            }
        } catch (IllegalStateException | NoSuchElementException e) {
        }
    }
}
