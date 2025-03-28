package nl.han;


import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameStatusText extends TextEntity {

    public GameStatusText(Coordinate2D position, String message) {
        super(position, message);
        setFill(Color.RED);
        setFont(Font.font("Arial", FontWeight.BOLD, 30));
    }

}

