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

import static nl.han.GameSettings.*;

public class GameScene extends DynamicScene implements KeyListener {

    private Random random;
    private List<YaegerEntity> activeEntities;
    private Timeline enemySpawner;
    private Timeline rocketSpawner;
    private ScoreText scoreText;
    private boolean gameFinished = true;
    private StartScreen startScreen;
    private Tank tank; // ✅ Store a reference to the tank

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
        SoundClip backgroundMusic = new SoundClip("Upbeat Pop Background Music - Party Time.mp3");
        backgroundMusic.play();
    }

    @Override
    public void setupEntities() {
        startScreen = new StartScreen(new Coordinate2D(350, 250));
        addGameEntity(startScreen);
    }

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

    private void startEnemySpawner() {
        enemySpawner = new Timeline(new KeyFrame(Duration.seconds(ENEMY_SPAWNER_TIME), event -> spawnEnemy()));
        enemySpawner.setCycleCount(Timeline.INDEFINITE);
        enemySpawner.play();
    }

    private void startRocketSpawner() {
        rocketSpawner = new Timeline(new KeyFrame(Duration.millis(ROCKET_SPAWNER_TIME), event -> spawnRocket()));
        rocketSpawner.setCycleCount(Timeline.INDEFINITE);
        rocketSpawner.play();
    }

    private void stopEnemySpawner() {
        if (this.enemySpawner != null) {
            this.enemySpawner.stop();
        }
    }

    private void stopRocketSpawner() {
        if (this.rocketSpawner != null) {
            this.rocketSpawner.stop();
        }
    }

    private void spawnEnemy() {
        double randomY = random.nextInt(500);
        Enemy enemy;
        int enemyType = random.nextInt(2);

        if (enemyType == 0) {
            enemy = new Stone(new Coordinate2D(WIDTH, randomY));
        } else {
            enemy = new Bomb(new Coordinate2D(WIDTH, randomY));
        }

        addGameEntity(enemy);
    }

    private void spawnRocket() {
        double randomY = random.nextInt(500);
        Rocket rocket = new Rocket(new Coordinate2D(WIDTH, randomY));
        addGameEntity(rocket);
    }

    public void addGameEntity(YaegerEntity entity) {
        addEntity(entity);
        activeEntities.add(entity);
    }

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

    private void clearEntities() {
        for (YaegerEntity entity : activeEntities) {
            entity.remove();
        }
        activeEntities.clear();
    }

    private void restartGame() {
        gameFinished = false;
        clearEntities();
        startGame();
    }

    public int getScore() {
        return scoreText.getScore();
    }

    public void increaseScore(int points) {
        scoreText.increaseScore(points);
    }

    public void decreaseScore(int points) {
        scoreText.decreaseScore(points);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (gameFinished && pressedKeys.contains(KeyCode.ENTER)) {
            restartGame();
        }
    }
}
