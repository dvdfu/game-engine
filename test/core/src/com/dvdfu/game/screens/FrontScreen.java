package com.dvdfu.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.dvdfu.game.MainGame;
import com.dvdfu.game.handlers.InputController;
import com.dvdfu.game.objects.Block;
import com.dvdfu.game.objects.Player;

public class FrontScreen extends AbstractScreen {
	Player sdf = new Player();
	SpriteBatch b = new SpriteBatch();
	Group blocks = new Group();
	public FrontScreen(MainGame game) {
		super(game);
		Gdx.input.setInputProcessor(new InputController());
		blocks.addActor(new Block());
	}

	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		sdf.update();
		sdf.collide(blocks);
		sdf.act(delta);
		b.begin();
		sdf.draw(b, 1);

		for (Actor a : blocks.getChildren()) {
			a.act(delta);
			a.draw(b, 1);
		}
		b.end();
	}

	public void resize(int width, int height) {
	}

	public void show() {
	}

	public void hide() {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
	}
}