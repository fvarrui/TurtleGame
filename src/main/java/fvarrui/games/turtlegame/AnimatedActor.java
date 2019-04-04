package fvarrui.games.turtlegame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedActor extends TexturedActor {

	private boolean pause;
	private float elapsedTime = 0f;
	private Animation<TextureRegion> animation;

	public AnimatedActor(Animation<TextureRegion> animation) {
		super(animation.getKeyFrame(0).getTexture());
		this.animation = animation;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (!pause)
			elapsedTime += delta;
	}
	
	public void pause() {
		this.pause = true;
	}
	
	public void play() {
		this.pause = false;
	}

	public void draw(Batch batch, float parentAlpha) {
		setTexture(animation.getKeyFrame(elapsedTime));
		super.draw(batch, parentAlpha);
	}

}
