package com.dvdfu.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dvdfu.game.MainGame;
import com.dvdfu.game.handlers.InputController;
import com.dvdfu.game.objects.Player;

public class FrontScreen extends AbstractScreen {
	Player sdf = new Player();
	SpriteBatch b = new SpriteBatch();
	public FrontScreen(MainGame game) {
		super(game);
		Gdx.input.setInputProcessor(new InputController());
	}

	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		sdf.act(delta);
		b.begin();
		sdf.draw(b, 1);
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