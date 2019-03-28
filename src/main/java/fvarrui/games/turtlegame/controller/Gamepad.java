package fvarrui.games.turtlegame.controller;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public class Gamepad {
	
	private static Controller gamepad;
	
	static {
		if (!Controllers.getControllers().isEmpty()) {
			gamepad = Controllers.getControllers().get(0);
		}
	}
	
	public static Controller getGamepad() {
		return gamepad;
	}

}
