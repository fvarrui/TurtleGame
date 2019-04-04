package fvarrui.games.turtlegame;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemFactory {

	public static Actor createItem(TiledMapTile tile, float x, float y) {

		TexturedActor actor = null;

		String name = (String) tile.getProperties().get("name");

		switch (name) {
		case "Starfish":
			actor = new Starfish();
			break;
		case "Rock":
			actor = new Rock();
			break;
		case "Plant0":
		case "Plant1":
		case "Plant2":
		case "Plant3":
			actor = new Plant();
			break;			
		}

		if (actor != null) {
			System.out.println(name + " texture=" + tile.getTextureRegion().getRegionX() + "," + tile.getTextureRegion().getRegionY());
			actor.setPosition(x, y);
			actor.setTexture(tile.getTextureRegion());
		}

		return actor;
	}

}
