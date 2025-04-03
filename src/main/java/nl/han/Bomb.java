package nl.han;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

// Vijand Bom
public class Bomb extends Enemy {
    public static final int BOMB_SPEED = 1;
    public static int BOMB_SIZE = 100;

    public Bomb(Coordinate2D initialLocation) {
        super("bomb.png", initialLocation, BOMB_SPEED, new Size(BOMB_SIZE, BOMB_SIZE));
    }
}