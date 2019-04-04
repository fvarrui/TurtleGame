package fvarrui.games.turtlegame.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fvarrui.games.turtlegame.utils.ItemFactory;

public class Ocean extends Actor {
	
	// window dimensions
	public static int windowWidth = 800;
	public static int windowHeight = 600;
	
	private List<Starfish> starfish = new ArrayList<>();
	private List<Rock> rocks = new ArrayList<>();
	private List<Plant> plants = new ArrayList<>();
	
	private TiledMap map;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer renderer;
	
	private TiledMapImageLayer backgroundLayer;
	private TiledMapTileLayer itemsLayer;
	
	public Vector2 startPosition;

	public Ocean(String mapFile) {
		
		// set up tile map, renderer, and camera
		map = new TmxMapLoader().load(mapFile);
		
		int tileWidth = (int) map.getProperties().get("tilewidth");
		int tileHeight = (int) map.getProperties().get("tileheight");
		int numTilesHorizontal = (int) map.getProperties().get("width");
		int numTilesVertical = (int) map.getProperties().get("height");
		
		setWidth(tileWidth * numTilesHorizontal);
		setHeight(tileHeight * numTilesVertical);
		
		MapObject start = map.getLayers().get("objects").getObjects().get("Start");
		startPosition = new Vector2((float) start.getProperties().get("x"), (float) start.getProperties().get("y"));
		
		backgroundLayer = (TiledMapImageLayer) map.getLayers().get("background");
		itemsLayer = (TiledMapTileLayer) map.getLayers().get("items");
		
		List<Actor> items = new ArrayList<Actor>();
		for (int x = 0; x < numTilesHorizontal; x++)
			for (int y = 0; y < numTilesVertical; y++)
				if (itemsLayer.getCell(x, y) != null) {
					TiledMapTile tile = itemsLayer.getCell(x, y).getTile();
					Actor actor = ItemFactory.createItem(tile, x * tileWidth, y * tileHeight);
					if (actor != null) items.add(actor);
				}
		
		starfish.addAll(items.stream().filter(i -> i != null && i instanceof Starfish).map(i -> (Starfish)i).collect(Collectors.toList()));
		rocks.addAll(items.stream().filter(i -> i != null && i instanceof Rock).map(i -> (Rock)i).collect(Collectors.toList()));
		plants.addAll(items.stream().filter(i -> i != null && i instanceof Plant).map(i -> (Plant)i).collect(Collectors.toList()));
		
		renderer = new OrthogonalTiledMapRenderer(map);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, windowWidth, windowHeight);
		camera.update();

	}
	
	public List<Starfish> getStarfish() {
		return starfish;
	}
	
	public List<Rock> getRocks() {
		return rocks;
	}
	
	public List<Plant> getPlants() {
		return plants;
	}

	public void draw(Batch batch, float parentAlpha) {
		
		// adjust tilemap camera to stay in sync with main camera
		Camera mainCamera = getStage().getCamera();
		camera.position.x = mainCamera.position.x;
		camera.position.y = mainCamera.position.y;
		camera.update();
		
		renderer.setView(camera);
		
		// need the following code to force batch order, otherwise it is batched and rendered last
		batch.end();
		
		// render only backgroup layer
		renderer.getBatch().begin();
		renderer.renderImageLayer(backgroundLayer);
		renderer.getBatch().end();
		
		batch.begin();
	}

}
