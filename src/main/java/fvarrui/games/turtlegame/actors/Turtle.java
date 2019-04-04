package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import fvarrui.games.turtlegame.controller.Gamepad;
import fvarrui.games.turtlegame.controller.PS4GamepadCodes;
import fvarrui.games.turtlegame.utils.Animations;

public class Turtle extends AnimatedActor {
	
	private Vector2 oldPosition = new Vector2();
	private float oldAngle = 0f;

	public Turtle() {
		super(Animations.loadFromFiles(0.05f, true, new String [] { 
				"assets/turtle-1.png", 
				"assets/turtle-2.png",
				"assets/turtle-3.png", 
				"assets/turtle-4.png", 
				"assets/turtle-5.png", 
				"assets/turtle-6.png" 
				}));
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		float velocity = 0f;
		float rotation = getRotation();

		oldAngle = getRotation();
		oldPosition.set(getX(), getY());

		// check keyboard input
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			velocity = 8.0f;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			velocity = -8.0f;
			rotation += 180;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			rotateBy(5);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			rotateBy(-5);
		}

		// check gamepad input
		if (Gamepad.getGamepad() != null) {
			float leftX = Gamepad.getGamepad().getAxis(PS4GamepadCodes.AXIS_LEFT_X);
			float leftY = Gamepad.getGamepad().getAxis(PS4GamepadCodes.AXIS_LEFT_Y);
			if (leftY > 0.1) {
				velocity = 8 * leftY;
				rotation += 180;
			}
			if (leftY < -0.1) {
				velocity = 8 * leftY;
			}
			if (leftX > 0.1 || leftX < -0.1) {
				rotateBy(5 * -leftX);
			}
		}

		Vector2 direction = new Vector2(Vector2.X);
		direction.setLength(velocity);
		direction.setAngle(rotation);
		
		if (velocity != 0f) play();
		else pause();
		
		moveBy(direction.x, direction.y);
		
	}
	
	public void undo() {
		setPosition(oldPosition.x, oldPosition.y);
		setRotation(oldAngle);
	}

}
