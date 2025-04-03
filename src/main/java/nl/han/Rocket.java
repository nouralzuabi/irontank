package nl.han;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

// Vijand Raket
public class Rocket extends Enemy {

    public static final int ROCKET_SPEED = 1;
    public static final int ROCKET_SIZE = 75;

    public Rocket(Coordinate2D initialLocation) {
        super("rocket.png", initialLocation, ROCKET_SPEED, new Size(ROCKET_SIZE, ROCKET_SIZE));
    }
}