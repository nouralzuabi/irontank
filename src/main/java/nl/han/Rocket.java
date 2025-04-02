package nl.han;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import static nl.han.GameSettings.ROCKET_SIZE;
import static nl.han.GameSettings.ROCKET_SPEED;

// Vijand Raket
public class Rocket extends Enemy {
    public Rocket(Coordinate2D initialLocation) {
        super("rocket.png", initialLocation, ROCKET_SPEED, new Size(ROCKET_SIZE, ROCKET_SIZE));
    }
}