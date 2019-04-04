package fvarrui.games.turtlegame;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

public class TurtleGame extends Game {
	
	private Sound winSound;
	private Sound eatSound;
	private Music wavesAmbient;

	private Stage gameStage;
	private Stage hudStage;
	
	private FPS fps;
	private Score score;
	private Ocean ocean;
	private Turtle turtle;
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
		turtle.setPosition(ocean.startPosition.x, ocean.startPosition.y);
//		turtle.setShowBounds(true);

		winMessage = new WinMessage();
		winMessage.setVisible(false);
		
		score = new Score();
		score.setPosition(10, Ocean.windowHeight - 10, Align.top);
		
		fps = new FPS();
		fps.setPosition(Ocean.windowWidth - 200, Ocean.windowHeight - 10, Align.topRight);

		gameStage = new Stage();
		gameStage.addActor(ocean);
		
		ocean.getStarfish().stream().forEach(starfish -> {
			gameStage.addActor(starfish);
		});
		
		ocean.getRocks().stream().forEach(rock -> {
			gameStage.addActor(rock);
		});
		
		ocean.getPlants().stream().forEach(plant -> {
			gameStage.addActor(plant);
		});
		
		gameStage.addActor(turtle);

		
		hudStage = new Stage();
		hudStage.addActor(score);
		hudStage.addActor(fps);
		hudStage.addActor(winMessage);

	}

	@Override
	public void render() {
	
		// check user input
		gameStage.act();
		hudStage.act();

		update();

		// draw graphics
		clearScreen();
		gameStage.draw();
		hudStage.draw();
		
	}

	// clear screen with black color
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void update() {
		
		// check if turtle eats starfish
		if (!winMessage.isVisible()) {
			
			List<Starfish> removed = new ArrayList<>();
			ocean.getStarfish().stream().filter(starfish -> starfish.overlaps(turtle)).forEach(starfish -> {
				eatSound.play();
				score.increase();
				starfish.remove();	
				removed.add(starfish);
				if (score.getScore() >= 10) {
					winMessage.setVisible(true);
					winSound.play();
				}
			});
			ocean.getStarfish().removeAll(removed);

		}
		
		// check if turtle hits a rock or a plant
		ocean.getRocks().stream().filter(rock -> rock.overlaps(turtle)).forEach(rock -> turtle.undo());

		// check if turtle goes out of screen on horizontal axis
		if (turtle.getX() < 0)
			turtle.setX(0);
		else if (turtle.getX() + turtle.getWidth() > ocean.getWidth())
			turtle.setX(ocean.getWidth() - turtle.getWidth());

		// check if turtle goes out of screen on vertical axis
		if (turtle.getY() < 0)
			turtle.setY(0);
		else if (turtle.getY() + turtle.getHeight() > ocean.getHeight())
			turtle.setY(ocean.getHeight() - turtle.getHeight());
		
		// center camera on turtle actor
		gameStage.getCamera().position.x = turtle.getX() + turtle.getOriginX();
		gameStage.getCamera().position.y = turtle.getY() + turtle.getOriginY();

	}

	public static void main(String[] args) {
		TurtleGame game = new TurtleGame();
		
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Turtle Game";
        cfg.width = Ocean.windowWidth;
        cfg.height = Ocean.windowHeight;
        cfg.resizable = false;
        cfg.fullscreen = true;
        
		new LwjglApplication(game, cfg);
	}

}
