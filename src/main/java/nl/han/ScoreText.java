package nl.han;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


// Extends de Yaeger build in TextEntity
public class ScoreText extends TextEntity {

    public static final int START_SCORE = 30; //het moet static zijn, want we gebruiken het voor dat super constrator aangeroepen is
    private int score = START_SCORE;

    public ScoreText(Coordinate2D position) {
        super(position, "Score: " + START_SCORE);
        setFill(Color.WHITE);
        setFont(Font.font("Arial", FontWeight.BOLD, 24));
    }

    // Verhoogt de Score
    public void increaseScore(int points) {
        score += points;
        setText("Score: " + score);
    }

    // Verlaagt de Score
    public void decreaseScore(int points) {
        score -= points;
        if (score < 0) {
            score = 0;
        }
        setText("Score: " + score);
    }

    // Return de Score
    public int getScore() {
        return score;
    }

    // Set de Score
    public void setScore(int score) {
        this.score = score;
        setText("Score: " + score);
    }
}