package com.dvdfu.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.dvdfu.game.handlers.Consts;
import com.dvdfu.game.handlers.Input;

public class Player extends GameObj {
	private Vector2 move = new Vector2();
	private Vector2 shift = new Vector2();
	private boolean grounded;

	public Player() {
		super();
		size.set(48, 48);
		sprite.set(Consts.player);
	}
	
	public void update() {
		if (Input.KeyDown(Input.ARROW_RIGHT) && move.x < 6) move.x += 0.4f;
		else if (Input.KeyDown(Input.ARROW_LEFT) && move.x > -6) move.x -= 0.4f;
		else {
			if (move.x > 0.4f) move.x -= 0.4f;
			else if (move.x < -0.4f) move.x += 0.4f;
			else move.x = 0;
		}

		if (grounded && Input.KeyPressed(Input.ARROW_UP)) {
			move.y = 10;
		}

		move.y -= 0.3f;
		grounded = false;
		vel.set(move.cpy().add(shift));
		
		if (vel.y < 0 && pos.y + vel.y < 0) {
			move.y = 0;
			pos.y = 0;
			grounded = true;
		}
	}

	public void act(float delta) {
		super.act(delta);
	}
	
	public void collide(Group blocks) {
		shift.set(0, 0);
		for (Actor a : blocks.getChildren()) {
			Block b = (Block) a;
			bound.setPosition(pos.x + vel.x, pos.y + vel.y);
			if (bound.overlaps(b.bound)) {
				shift.set(b.vel);
				if (pos.y >= b.bound.getTop() + b.vel.y && vel.y - b.vel.y < 0) {
					vel.y = 0;
					move.y = 0;
					pos.y = b.bound.getTop();
					grounded = true;
				} else if (pos.y + size.y <= b.bound.getY() + b.vel.y && vel.y - b.vel.y > 0) {
					vel.y = 0;
					move.y = 0;
					pos.y = b.bound.getY() - size.y;
				} else if (pos.x >= b.bound.getRight() + b.vel.x && vel.x - b.vel.x < 0) {
					vel.x = 0;
					move.x = 0;
					pos.x = b.bound.getRight();
				} else if (pos.x + size.x <= b.bound.getX() + b.vel.x && vel.x - b.vel.x > 0) {
					vel.x = 0;
					move.x = 0;
					pos.x = b.bound.getX() - size.x;
				}
			}
		}
	}
}
