package nl.han;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.Coordinate2D;
import java.util.List;

public class Bullet extends DynamicSpriteEntity implements Collided {

    private final double speed = 8;
    public final int maxScore = 100;
    private final GameScene gameScene;

    public final int increaseRocketPoints = 2;
    public final int increaseStonePoints = 10;
    public final int increaseBombPoints = 20;

    public Bullet(Coordinate2D initialLocation, GameScene gameScene) {
        super("bullet.png",  initialLocation, new Size(40, 40));
        this.gameScene = gameScene;
        setMotion(speed, 90); // Bullet beweegt van Links naar Rechts
    }

    // Verhoogt de punten met welke Entity we in aanmerking komen.
    @Override
    public void onCollision(List<Collider> collidingEntities) {
        System.out.println("Bullet collided with something!");

        for (Collider entity : collidingEntities) {
            if (entity instanceof Rocket) {
                handleCollision((DynamicSpriteEntity) entity, increaseRocketPoints);
                break;
            } else if (entity instanceof Stone) {
                handleCollision((DynamicSpriteEntity) entity, increaseStonePoints);
                break;
            } else if (entity instanceof Bomb) {
                handleCollision((DynamicSpriteEntity) entity, increaseBombPoints);
                break;
            }
        }
    }

    // Verwijderd de Enemy Entity
    private void handleCollision(DynamicSpriteEntity enemy, int scoreIncrease) {
        System.out.println("Collision with " + enemy.getClass().getSimpleName() + " detected. Removing enemy and bullet.");

        enemy.remove();
        this.remove();

        if (gameScene != null) {
            gameScene.increaseScore(scoreIncrease);
            System.out.println("Score updated! New Score: " + gameScene.getScore());

            if (gameScene.getScore() >= maxScore) {
                gameScene.youWin();
            }
        }
    }
}