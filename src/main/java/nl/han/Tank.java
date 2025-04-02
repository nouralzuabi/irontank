package nl.han;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.Set;
import static nl.han.GameSettings.*;

public class Tank extends SpriteEntity implements KeyListener, Collided {

    private static final double SPEED = 5;
    private final GameScene gameScene;
    private SoundClip shootingSound;
    private boolean isShooting = false;
    private boolean isActive = true;

    public Tank(Coordinate2D initialLocation, GameScene gameScene) {
        super("tank.png", initialLocation, new Size(TANK_SIZE, TANK_SIZE));
        this.gameScene = gameScene;
        this.shootingSound = new SoundClip("shoot.mp3");
    }

    // Deactiveer
    public void deactivate() {
        isActive = false;
    }

    // Omgaan met drukken op Knoppen
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (!isActive) return;

        double newY = getAnchorLocation().getY();

        if (pressedKeys.contains(KeyCode.UP)) {
            newY -= SPEED;
        }
        if (pressedKeys.contains(KeyCode.DOWN)) {
            newY += SPEED;
        }

        double minY = 0;
        double maxY = gameScene.getHeight() - getHeight();
        newY = Math.max(minY, Math.min(newY, maxY));

        setAnchorLocation(new Coordinate2D(getAnchorLocation().getX(), newY));

        if (pressedKeys.contains(KeyCode.SPACE) && !isShooting) {
            isShooting = true;
            shootingSound.play();
            fireBullet();
        } else if (!pressedKeys.contains(KeyCode.SPACE)) {
            isShooting = false;
        }
    }

    // Tank schiet een Bullet
    private void fireBullet() {
        Bullet bullet = new Bullet(new Coordinate2D(getAnchorLocation().getX() + 50, getAnchorLocation().getY() + 10), gameScene); // Bullet op de correcte begin locatie
        gameScene.addGameEntity(bullet);
    }

    // Verlaagt de punten met welke Entity we in aanmerking komen.
    @Override
    public void onCollision(List<Collider> collidingEntities) {
        if (!isActive) return;

        for (Collider entity : collidingEntities) {
            if (entity instanceof Rocket) {
                handleTankCollision((DynamicSpriteEntity) entity, DECREASE_ROCKET_POINTS);
                break;
            } else if (entity instanceof Stone) {
                handleTankCollision((DynamicSpriteEntity) entity, DECREASE_STONE_POINTS);
                break;
            } else if (entity instanceof Bomb) {
                handleTankCollision((DynamicSpriteEntity) entity, DECREASE_BOMB_POINTS);
                break;
            }
        }
    }

    // Verwijderd de Enemy Entity
    private void handleTankCollision(DynamicSpriteEntity enemy, int scorePenalty) {
        enemy.remove();

        gameScene.decreaseScore(scorePenalty);

        // Checkt of het Spel is afgelopen
        if (gameScene.getScore() <= 0) {
            gameScene.gameOver();
        }
    }
}