package fvarrui.games.turtlegame;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

import fvarrui.games.turtlegame.actors.FPS;
import fvarrui.games.turtlegame.actors.Ocean;
import fvarrui.games.turtlegame.actors.Score;
import fvarrui.games.turtlegame.actors.Starfish;
import fvarrui.games.turtlegame.actors.Turtle;
import fvarrui.games.turtlegame.actors.WinMessage;

public class GameScreen implements Screen {
	
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
	
	public GameScreen(Game game) {

		// init ambient sound
		wavesAmbient = Gdx.audio.newMusic(Gdx.files.internal("assets/ocean-waves.ogg"));
		wavesAmbient.setVolume(0.25f);
		wavesAmbient.setLooping(true);
		
		// init discrete sounds
		eatSound = Gdx.audio.newSound(Gdx.files.internal("assets/eat.ogg"));
		winSound = Gdx.audio.newSound(Gdx.files.internal("assets/win.ogg"));
		
		// create actors
		ocean = new Ocean("assets/map02.tmx");

		turtle = new Turtle();
		turtle.setPosition(ocean.startPosition.x, ocean.startPosition.y);

		winMessage = new WinMessage();
		winMessage.setVisible(false);
		
		score = new Score();
		score.setPosition(10, Ocean.windowHeight - 10, Align.top);
		
		fps = new FPS();
		fps.setPosition(Ocean.windowWidth - 200, Ocean.windowHeight - 10, Align.topRight);

		// create game stage and add actors to be rendered in order
		gameStage = new Stage();
		gameStage.addActor(ocean);
		ocean.getRocks().stream().forEach(rock -> gameStage.addActor(rock));
		ocean.getPlants().stream().forEach(plant -> gameStage.addActor(plant));
		ocean.getStarfish().stream().forEach(starfish -> gameStage.addActor(starfish));
		gameStage.addActor(turtle);

		// create HUD stage and add actors
		hudStage = new Stage();
		hudStage.addActor(score);
		hudStage.addActor(fps);
		hudStage.addActor(winMessage);	
	}

	@Override
	public void render(float delta) {
		
		// check user input and update actors
		gameStage.act();
		hudStage.act();

		// update camera position and check collisions
		update();

		// draw graphics
		clearScreen();
		gameStage.draw();
		hudStage.draw();
	}
	
	@Override
	public void show() {
		System.out.println("show");
		wavesAmbient.play();				
	}	

	@Override
	public void resize(int width, int height) {
		System.out.println("resize");
	}

	@Override
	public void pause() {
		System.out.println("pause");	
		wavesAmbient.pause();
	}

	@Override
	public void resume() {
		System.out.println("resume");	
		wavesAmbient.play();		
	}

	@Override
	public void hide() {
		System.out.println("hide");				
	}

	@Override
	public void dispose() {
		gameStage.dispose();
		hudStage.dispose();
	}
	
	/**
	 * clear screen with black color
	 */
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * update camera position and check collisions
	 */
	private void update() {
		
		// check if turtle eats starfish
		if (!winMessage.isVisible()) {
			
			List<Starfish> removed = new ArrayList<>();
			ocean.getStarfish().stream().filter(starfish -> starfish.overlaps(turtle)).forEach(starfish -> {
				eatSound.play();
				score.increase();
				starfish.remove();	
				removed.add(starfish);
			});
			ocean.getStarfish().removeAll(removed);
			if (ocean.getStarfish().isEmpty()) {
				winMessage.setVisible(true);
				winSound.play();
			}
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

}
