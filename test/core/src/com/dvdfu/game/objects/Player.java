package com.dvdfu.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.dvdfu.game.handlers.Input;

public class Player extends GameObj {
	private Texture bg;
	private Vector2 move;

	public Player() {
		super();
		size.set(64, 64);
		move = new Vector2();
		bg = new Texture(Gdx.files.internal("blank.png"));
	}

	public void act(float delta) {
		if (Input.KeyDown(Input.ARROW_RIGHT) && move.x < 6) move.x += 0.4f;
		else if (Input.KeyDown(Input.ARROW_LEFT) && move.x > -6) move.x -= 0.4f;
		else {
			if (move.x > 0.4f) move.x -= 0.4f;
			else if (move.x < -0.4f) move.x += 0.4f;
			else move.x = 0;
		}

		if (Input.KeyPressed(Input.ARROW_UP)) {
			move.y = 10;
		}
		
		if (move.y < 0 && pos.y + move.y < 0) {
			move.y = 0;
			pos.y = 0;
		} else {
			move.y -= 0.3f;
		}
		
		vel.add(move);
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, pos.x, pos.y, size.x, size.y);
	}

	public void reset() {
	}
}
