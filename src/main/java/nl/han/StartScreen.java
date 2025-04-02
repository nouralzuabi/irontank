package nl.han;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Begin Scherm
public class StartScreen extends TextEntity {

    public StartScreen(Coordinate2D position) {
        super(position, "PRESS ENTER TO START");
        setFill(Color.WHITE);
        setFont(Font.font("Arial", FontWeight.BOLD, 36));
    }
}