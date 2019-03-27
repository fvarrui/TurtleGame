package fvarrui.games.turtlegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TexturedActor extends Actor {

	private TextureRegion texture = new TextureRegion();

	public TexturedActor(Texture texture) {
		super();
		setTexture(texture);
	}

	public void setTexture(Texture texture) {
		this.texture.setRegion(texture);
		setSize(texture.getWidth(), texture.getHeight());
		setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public boolean overlaps(TexturedActor actor) {
		return this.getBounds().overlaps(actor.getBounds());
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(getColor());
		if (isVisible()) {
			batch.draw(
					texture, 
					getX(), getY(), 
					getOriginX(), getOriginY(), 
					getWidth(), getHeight(), 
					getScaleX(), getScaleY(), 
					getRotation()
				);
		}
	}
	
	public void center() {
		setPosition((Gdx.graphics.getWidth() - getWidth()) / 2, (Gdx.graphics.getHeight() - getHeight()) / 2);
	}

}
