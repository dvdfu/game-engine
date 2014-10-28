package com.dvdfu.game.visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	private TextureRegion[] frames;
	private int length;
	private int width;
	private int height;

	public Animation(String filename, int width, int height) {
		set(new Texture(Gdx.files.internal(filename)), width, height);
	}

	public Animation(Texture sprite, int width, int height) {
		set(sprite, width, height);
	}

	public void set(Texture texture, int width, int height) {
		length = (int) (texture.getWidth() / width);
		frames = new TextureRegion[length];
		for (int i = 0; i < length; i++) {
			frames[i] = new TextureRegion(texture, i * width, 0, width, height);
		}
		this.width = frames[0].getRegionWidth();
		this.height = frames[0].getRegionHeight();
	}

	public TextureRegion getFrame(int frame) {
		return frames[frame % length];
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}