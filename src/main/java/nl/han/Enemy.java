package nl.han;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.Coordinate2D;

public abstract class Enemy extends DynamicSpriteEntity implements Collider {

    public Enemy(String resource, Coordinate2D initialLocation, double speed, Size size) {
        super(resource, initialLocation, size); // ✅ Set custom size
        setMotion(speed, 270); // Move left toward the tank
    }
}
