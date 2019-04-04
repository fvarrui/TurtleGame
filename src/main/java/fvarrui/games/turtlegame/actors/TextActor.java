package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class TextActor extends BaseActor {

	private BitmapFont font;
	private String text;
	
	public TextActor(String fontFile, int size) {
		this(fontFile, size, false);
	}

	public TextActor(String fontFile, int size, boolean shadow) {
		super();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		if (shadow) {
			parameter.shadowColor = new Color(0, 0, 0, 0.5f);
			parameter.shadowOffsetX = 5;
			parameter.shadowOffsetY = 5;
		}
		font = generator.generateFont(parameter);
		generator.dispose();
		setText("");
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.setColor(getColor());
		font.draw(batch, text, getX(), getY() + getHeight());
		super.draw(batch, parentAlpha);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		updateSize();
	}
	
	private void updateSize() {
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, getText());
		setSize(layout.width, layout.height);
	}

}
