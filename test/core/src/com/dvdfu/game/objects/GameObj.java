package com.dvdfu.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dvdfu.game.handlers.Bound;
import com.dvdfu.game.visuals.GameSprite;

public abstract class GameObj extends Actor {
	protected Vector2 pos = new Vector2();
	protected Vector2 size = new Vector2();
	protected Vector2 vel = new Vector2();
	protected Bound bound = new Bound();
	protected GameSprite sprite = new GameSprite();

	public GameObj() {
		super();
	}

	public void act(float delta) {
		pos.add(vel);
		bound.set(pos.x, pos.y, size.x, size.y);
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		sprite.update();
		batch.draw(sprite.getFrame(), pos.x, pos.y, size.x, size.y);
	}
}
