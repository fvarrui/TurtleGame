package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseActor extends Actor {

	private boolean showBounds = false;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	public Polygon getBounds() {
		Polygon bounds = new Polygon(new float[] { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight() });
		bounds.setPosition(getX(), getY());
		bounds.setOrigin(getOriginX(), getOriginY());
		bounds.setRotation(getRotation());
		return bounds;
	}

	public boolean overlaps(BaseActor actor) {
		return Intersector.overlapConvexPolygons(getBounds(), actor.getBounds());
	}

	public void center() {
		setPosition((Gdx.graphics.getWidth() - getWidth()) / 2, (Gdx.graphics.getHeight() - getHeight()) / 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (showBounds) {
			batch.end();
			shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(Color.YELLOW);
			shapeRenderer.polygon(getBounds().getTransformedVertices());
			shapeRenderer.end();
			batch.begin();
		}
	}

	public void setShowBounds(boolean showBounds) {
		this.showBounds = showBounds;
	}

}
