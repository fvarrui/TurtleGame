package fvarrui.games.turtlegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TurtleGame extends Game {
	
	private int score = 0;
	
	private Sound winSound;
	private Sound eatSound;
	private Music wavesAmbient;

	private Stage stage;
	private Ocean ocean;
	private Turtle turtle;
	private Starfish starfish;
	private WinMessage winMessage;

	public void create() {
		
		wavesAmbient = Gdx.audio.newMusic(Gdx.files.internal("assets/ocean-waves.ogg"));
		wavesAmbient.setVolume(0.25f);
		wavesAmbient.setLooping(true);
		wavesAmbient.play();
		
		eatSound = Gdx.audio.newSound(Gdx.files.internal("assets/eat.ogg"));

		winSound = Gdx.audio.newSound(Gdx.files.internal("assets/win.ogg"));

		ocean = new Ocean();

		turtle = new Turtle();
		turtle.setPosition(20, 20);

		starfish = new Starfish();
		starfish.setPosition(380, 380);

		winMessage = new WinMessage();
		winMessage.setVisible(false);

		stage = new Stage();
		stage.addActor(ocean);
		stage.addActor(turtle);
		stage.addActor(starfish);
		stage.addActor(winMessage);

	}

	@Override
	public void render() {

		// check user input
		stage.act();

		checkCollisions();

		clearScreen();

		// draw graphics
		stage.draw();

	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void checkCollisions() {
		// check if turtle eats starfish
		if (!winMessage.isVisible() && turtle.overlaps(starfish)) {
			eatSound.play();
			score++;
			if (score >= 10) {
				starfish.remove();
				winMessage.setVisible(true);
				winSound.play();
			} else {
				starfish.setPosition((float) (Math.random() * (Gdx.graphics.getWidth() - starfish.getWidth())), (float)(Math.random() * (Gdx.graphics.getHeight() - starfish.getHeight())));
			}
		}

		// check if turtle goes out of screen on horizontal axis
		if (turtle.getX() + turtle.getWidth() < 0)
			turtle.setX(Gdx.graphics.getWidth());
		else if (turtle.getX() > Gdx.graphics.getWidth())
			turtle.setX(-turtle.getWidth());

		// check if turtle goes out of screen on vertical axis
		if (turtle.getY() + turtle.getHeight() < 0)
			turtle.setY(Gdx.graphics.getHeight());
		else if (turtle.getY() > Gdx.graphics.getHeight())
			turtle.setY(-turtle.getHeight());
	}

	public static void main(String[] args) {
		Game game = new TurtleGame();
		
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Turtle Game";
        cfg.width = 800;
        cfg.height = 600;
        cfg.resizable = false;
        cfg.fullscreen = true;
        
		new LwjglApplication(game, cfg);
	}

}
