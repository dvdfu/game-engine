package com.dvdfu.game.objects;

import com.badlogic.gdx.math.MathUtils;

public class Block extends GameObj {
	float timer;

	public Block() {
		super();
		size.set(90, 90);
		pos.set(300, 48);
		bound.set(pos.x, pos.y, size.x, size.y);
	}

	public void act(float delta) {
		timer += delta / 5;
		while (timer > 1) {
			timer--;
		}
//		vel.set(MathUtils.cos(MathUtils.PI2 * timer),
//				MathUtils.sin(MathUtils.PI2 * timer));
		super.act(delta);
	}
}
