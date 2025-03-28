package nl.han;

import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static nl.han.GameSettings.START_SCORE;

public class ScoreText extends TextEntity {
   private int score = START_SCORE;

    public ScoreText(Coordinate2D position) {
        super(position, "Score: " + START_SCORE);
        setFill(Color.WHITE);
        setFont(Font.font("Arial", FontWeight.BOLD, 24));
    }

    public void increaseScore(int points) {
        score += points;
        setText("Score: " + score);
    }

    public void decreaseScore(int points) {
        score -= points;
        if (score < 0) {
            score = 0;
        }
        setText("Score: " + score);
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        setText("Score: " + score);
    }

}
