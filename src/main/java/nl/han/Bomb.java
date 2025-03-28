package nl.han;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

import static nl.han.GameSettings.BOMB_SIZE;
import static nl.han.GameSettings.BOMB_SPEED;

public class Bomb extends Enemy {
    public Bomb(Coordinate2D initialLocation) {
        super("bomb.png", initialLocation, BOMB_SPEED, new Size(BOMB_SIZE, BOMB_SIZE));
    }
}
