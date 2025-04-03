package nl.han;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameScene extends DynamicScene implements KeyListener {

    private final Random random;
    private final List<YaegerEntity> activeEntities;
    private Timeline enemySpawner;
    private Timeline rocketSpawner;
    private ScoreText scoreText;
    private boolean gameFinished = true;
    private StartScreen startScreen;
    private Tank tank;
    public final int enemySpawnerTime = 3;
    public final int rocketSpawnerTime = 500;

    public final int width = 1024;
    public final int height = 600;

    public GameScene() {
        this.random = new Random();
        this.activeEntities = new ArrayList<>();
    }

    @Override
    public void setupScene() {
        setBackgroundImage("background.jpeg");
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        SoundClip backgroundMusic = new SoundClip("Hitman.mp3");
        backgroundMusic.play();
    }

    @Override
    public void setupEntities() {
        startScreen = new StartScreen(new Coordinate2D(350, 250));
        addGameEntity(startScreen);
    }

    // Start het Spel
    private void startGame() {
        gameFinished = false;
        startScreen.remove();
        clearEntities();

        if (tank != null) {
            tank.remove();
        }

        tank = new Tank(new Coordinate2D(50, 250), this);
        addGameEntity(tank);

        scoreText = new ScoreText(new Coordinate2D(20, 20));
        addGameEntity(scoreText);

        startEnemySpawner();
        startRocketSpawner();
    }

    // Begint met Vijanden te laten verschijnen
    private void startEnemySpawner() {
        enemySpawner = new Timeline(new KeyFrame(Duration.seconds(enemySpawnerTime), event -> spawnEnemy()));
        enemySpawner.setCycleCount(Timeline.INDEFINITE);
        enemySpawner.play();
    }

    // Begint met Rockets te laten verschijnen
    private void startRocketSpawner() {
        rocketSpawner = new Timeline(new KeyFrame(Duration.millis(rocketSpawnerTime), event -> spawnRocket()));
        rocketSpawner.setCycleCount(Timeline.INDEFINITE);
        rocketSpawner.play();
    }

    // Stopt met Vijanden te laten verschijnen
    private void stopEnemySpawner() {
        if (this.enemySpawner != null) {
            this.enemySpawner.stop();
        }
    }

    // Stopt met Rockets te laten verschijnen
    private void stopRocketSpawner() {
        if (this.rocketSpawner != null) {
            this.rocketSpawner.stop();
        }
    }

    // Verschijnt een Enemy
    private void spawnEnemy() {
        double randomY = random.nextInt(500);
        Enemy enemy;
        int enemyType = random.nextInt(2);

        if (enemyType == 0) {
            enemy = new Stone(new Coordinate2D(width, randomY));
        } else {
            enemy = new Bomb(new Coordinate2D(width, randomY));
        }
        addGameEntity(enemy);
    }

    // Verschijnt een Rocket
    private void spawnRocket() {
        double randomY = random.nextInt(500);
        Rocket rocket = new Rocket(new Coordinate2D(width, randomY));
        addGameEntity(rocket);
    }

    // Voegt een Entity toe
    public void addGameEntity(YaegerEntity entity) {
        addEntity(entity);
        activeEntities.add(entity);
    }

    // Scherm Veloren
    public void gameOver() {
        stopEnemySpawner();
        stopRocketSpawner();
        clearEntities();
        gameFinished = true;

        if (tank != null) {
            tank.deactivate();
        }

        GameStatusText gameStatusText = new GameStatusText(new Coordinate2D(400, 250), "GAME OVER! Press ENTER to Restart");
        addGameEntity(gameStatusText);
    }

    // Scherm Gewonnen
    public void youWin() {
        stopEnemySpawner();
        stopRocketSpawner();
        clearEntities();
        gameFinished = true;

        if (tank != null) {
            tank.deactivate();
        }

        GameStatusText youWinText = new GameStatusText(new Coordinate2D(400, 250), "YOU WIN! Press ENTER to Restart");
        addGameEntity(youWinText);
    }

    // Verwijdert Alle Entities
    private void clearEntities() {
        for (YaegerEntity entity : activeEntities) {
            entity.remove();
        }
        activeEntities.clear();
    }

    // Herstart het Spel
    private void restartGame() {
        gameFinished = false;
        clearEntities();
        startGame();
    }

    public int getScore() {
        return scoreText.getScore();
    }

    // Verhoogt de Score
    public void increaseScore(int points) {
        scoreText.increaseScore(points);
    }

    // Verlaagt de Score
    public void decreaseScore(int points) {
        scoreText.decreaseScore(points);
    }

    // Herstart het Spel als er op Enter wordt gedrukt
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (gameFinished && pressedKeys.contains(KeyCode.ENTER)) {
            restartGame();
        }
    }
}