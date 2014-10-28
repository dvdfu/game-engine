package com.dvdfu.game.visuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dvdfu.game.handlers.Consts;

public class GameSprite {
	private Animation animation;
	private int timer;
	private int frameNum;

	public GameSprite() {
		animation = Consts.block;
	}

	public void update() {
		timer++;
		while (timer > Consts.TPF) {
			timer -= Consts.TPF;
			frameNum++;
			while (frameNum > animation.getLength()) {
				frameNum -= animation.getLength();
			}
		}
	}
	
	public void set(Animation animation) {
		this.animation = animation;
	}

	public TextureRegion getFrame() {
		return animation.getFrame(frameNum);
	}
}
