package com.dvdfu.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.dvdfu.game.handlers.Bound;

public abstract class GameObj extends Actor implements Poolable {
	protected Vector2 pos;
	protected Vector2 size;
	protected Vector2 vel;
	protected Bound bound;

	public GameObj() {
		super();
		pos = new Vector2();
		size = new Vector2();
		vel = new Vector2();
		bound = new Bound();
	}

	public void act(float delta) {
		pos.add(vel);
		bound.set(pos.x, pos.y, size.x, size.y);
		super.act(delta);
		vel.set(0, 0);
	}
}
