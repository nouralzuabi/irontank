package nl.han;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

import static nl.han.GameSettings.STONE_SIZE;
import static nl.han.GameSettings.STONE_SPEED;

public class Stone extends Enemy {
    public Stone(Coordinate2D initialLocation) {
        super("stone.png", initialLocation, STONE_SPEED, new Size(STONE_SIZE, STONE_SIZE));
    }
}
