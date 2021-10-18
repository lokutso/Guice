package application;

import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileLogger implements Logger{
    @NotNull
    private final CounterLines counterLines;
    @NotNull
    private final String tag;
    @NotNull
    private File file;

    @Inject
    public FileLogger(@NotNull CounterLines counterLines, @NotNull String tag) {
        this.counterLines = counterLines;
        this.tag = tag;
        Date date = new Date();
        file = new File("log" + date.getTime() + ".txt");
    }

    @Override
    public void createLog(@NotNull String message) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(log(message));
            fileWriter.close();
            counterLines.increase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private @NotNull String log(@NotNull String message) {
        return counterLines.getScore() + " : " + "<" + tag + ">" + message + "<" + tag + ">" + "\n";
    }
}
