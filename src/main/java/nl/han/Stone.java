package nl.han;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

// Vijand Steen
public class Stone extends Enemy {
    public static final  int STONE_SPEED = 1;
    public static final  int STONE_SIZE = 120;

    public Stone(Coordinate2D initialLocation) {
        super("stone.png", initialLocation, STONE_SPEED, new Size(STONE_SIZE, STONE_SIZE));
    }
}