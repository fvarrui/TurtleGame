package fvarrui.games.turtlegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TurtleGame extends Game {

	private Stage stage;
	private Ocean ocean;
	private Turtle turtle;
	private Starfish starfish;
	private WinMessage winMessage;
	
	public void create() {

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
		
		// check if turtle eats starfish
		if (turtle.overlaps(starfish)) {
			starfish.remove();
			winMessage.setVisible(true);
		}
		
		// check if turtle goes out of screen on horizontal axis
		if (turtle.getX() + turtle.getWidth() < 0) turtle.setX(Gdx.graphics.getWidth());
		else if (turtle.getX() > Gdx.graphics.getWidth()) turtle.setX(-turtle.getWidth());
	
		// check if turtle goes out of screen on vertical axis
		if (turtle.getY() + turtle.getHeight() < 0) turtle.setY(Gdx.graphics.getHeight());
		else if (turtle.getY() > Gdx.graphics.getHeight()) turtle.setY(-turtle.getHeight());
		
		// clear screen
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// draw graphics
		stage.draw();
		
	}

	public static void main(String[] args) {
		Game game = new TurtleGame();
		new LwjglApplication(game, "Turtle Game", 640, 480);
	}

}
