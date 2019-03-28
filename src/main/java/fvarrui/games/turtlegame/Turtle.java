package fvarrui.games.turtlegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import fvarrui.games.turtlegame.controller.Gamepad;
import fvarrui.games.turtlegame.controller.PS4GamepadCodes;

public class Turtle extends TexturedActor {
	
	public Turtle() {
		super(new Texture(Gdx.files.internal("assets/turtle-1.png")));
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		float velocity = 0f;
		float rotation = getRotation();
		
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
		
		Vector2 direction = new Vector2(Vector2.X);
		direction.setLength(velocity);
		direction.setAngle(rotation);

		moveBy(direction.x, direction.y); 
	}

}
