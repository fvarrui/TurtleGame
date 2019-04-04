package fvarrui.games.turtlegame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fvarrui.games.turtlegame.actors.Ocean;

public class DesktopLauncher {
	
	public static void main(String[] args) {
		
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Turtle Game";
        cfg.width = Ocean.windowWidth;
        cfg.height = Ocean.windowHeight;
        cfg.resizable = false;
        cfg.fullscreen = true;
        
		new LwjglApplication(new TurtleGame(), cfg);
		
	}

}
