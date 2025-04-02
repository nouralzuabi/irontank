package nl.han;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.Coordinate2D;

// Abstract Idee die later wordt geimplementeerd bij verschillende vijanden
public abstract class Enemy extends DynamicSpriteEntity implements Collider {

    public Enemy(String resource, Coordinate2D initialLocation, double speed, Size size) {
        super(resource, initialLocation, size);
        setMotion(speed, 270); // Vijand beweegt van Rechts naar Links
    }
}