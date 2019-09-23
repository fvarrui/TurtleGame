package fvarrui.games.turtlegame.actors;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.behaviors.FollowPath;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import fvarrui.games.turtlegame.utils.TexturedActor;

public class Shark extends TexturedActor implements Steerable<Vector2> {
	
	private FollowPath<Vector2, LinePath.LinePathParam> behaviour;
	private LinePath<Vector2> path;
	private Vector2 [] waypoints = { new Vector2(0,0), new Vector2(100,200)};

	public Shark() {
		super("assets/sharky.png");
		path = new LinePath<Vector2>(new Array<Vector2>(waypoints));
		behaviour = new FollowPath<Vector2, LinePath.LinePathParam>(this, path);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
	}

	@Override
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	@Override
	public float getOrientation() {
		return getRotation() * MathUtils.degreesToRadians;
	}

	@Override
	public void setOrientation(float orientation) {
		setRotation(orientation * MathUtils.radiansToDegrees);
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return vector.angleRad();
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.setLength(1f);
		outVector.setAngle(angle * MathUtils.radiansToDegrees);
		return outVector.nor();
	}

	@Override
	public Location<Vector2> newLocation() {
		return this;
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxLinearSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxLinearAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxAngularSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getMaxAngularAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getAngularVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBoundingRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTagged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTagged(boolean tagged) {
		// TODO Auto-generated method stub
		
	}


}
