package nl.han;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import static nl.han.GameSettings.HEIGHT;
import static nl.han.GameSettings.WIDTH;

public class GameWorld extends YaegerGame {

    @Override
    public void setupGame() {
        setGameTitle("Iron Tank");
        setSize(new Size(WIDTH, HEIGHT));
    }

    @Override
    public void setupScenes() {
        addScene(0, new GameScene());
        setActiveScene(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}