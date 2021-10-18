package application;

import com.google.inject.Inject;

public class CounterLines {
    private int score;

    @Inject
    public CounterLines() {
        score = 1;
    }


    public int getScore() {
        return this.score;
    }

    void increase() {
        score += 1;
    }
}
