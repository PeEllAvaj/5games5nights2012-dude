package com.mortalpowers.dude.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.loaders.obj.ObjLoader;

public class Player {
	float glX;
	float glY;
	Mesh model;
	/**
	 * tallness is between 0 and 2
	 */
	private float tallness = 2;
	private float speedX = 0.01f;
	private float speedY = 0;
	
	public Player() {
		InputStream stream=null;
       	stream = Gdx.files.internal("data/sphere.obj").read();

        setPosition(2,0);
        
        model = ObjLoader.loadObj(stream, true);
	}
	
	public void render() {
		Gdx.gl10.glTranslatef(glX,glY,-3.0f);
		Gdx.gl10.glScalef(1.0f,tallness,1.0f);
		
		model.render(GL10.GL_TRIANGLES);
	}
	
	public void setPosition(float x,float y) {
		x = Util.convertXFromBlocksToGL(x);
		y = Util.convertYFromBlocksToGL(y);
		glX = x;
		glY = y;
	}
	
	public BoundingBox getBoundingBox() {
		return new BoundingBox(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight());
	}
	
	public float getPositionX() {
		return Util.convertXFromGLToBlocks(glX);
	}
	
	public float getPositionY() {
		return Util.convertYFromGLToBlocks(glY);
	}
	
	public float getSpeedX() {
		return speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}
	
	public void setSpeedX(float s) {
		speedX = s;
	}
	
	public void setSpeedY(float s) {
		speedY = s;
	}
	
	public float getWidth() {
		return 1;
	}
	
	public float getHeight() {
		return 2;
	}
	
	/**
	 * Set the tallness of the player. Automatically maps slider between max and min.
	 * @param t position of the slider (0-1)
	 */
	public void setTallness(float t) {
		float minScale = .5f;
		float maxScale = 1.8f;
		tallness = t*(maxScale-minScale)+minScale;
	}

	/**
	 * Does the player have the ability to jump
	 * @TODO IMPLEMENT COLLISION DETECTION HERE AGAIN
	 * @return True if they are close enough to ground or object.
	 */
	public boolean hasFooting() {
		return true;
		
	}
}
