package nl.han;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;

public class GameWorld extends YaegerGame {

    GameScene scene = new GameScene();

    @Override
    public void setupGame() {
        setGameTitle("Iron Tank");
        setSize(new Size(scene.width, scene.height));
    }

    @Override
    public void setupScenes() {

        addScene(0, scene);
        setActiveScene(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}