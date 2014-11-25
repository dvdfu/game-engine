package com.dvdfu.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.math.MathUtils;
import com.dvdfu.game.MainGame;
import com.dvdfu.game.TestShader;

public class TestScreen extends AbstractScreen {
	private OrthographicCamera cam;
	private CameraInputController camController;
	private ModelBatch modelBatch;
	private Model model;
	private ArrayList<ModelInstance> instances;
	private Environment environment;
	private Renderable renderable;
	private RenderContext renderContext;
	private Shader shader;
	private float timer = 0;

	public TestScreen(MainGame game) {
		super(game);
		// Gdx.input.setInputProcessor(new InputController());

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.2f, 1f));
		environment.add(new DirectionalLight().set(0.95f, 0.9f, 0.75f, -1.5f, -1f, -0.5f));
		environment.add(new DirectionalLight().set(0.11f, 0.08f, 0.14f, 1.5f, 1.0f, 0.5f)); // direct light

		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    cam.position.set(0, 1, 0);
	    cam.lookAt(0, 0, 0);
		cam.near = -3000f; // defines closest distance to draw
		cam.far = 3000f; // defines farthest distance to draw
		cam.update();
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);

		modelBatch = new ModelBatch();

		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createSphere(320, 320, 320, 40, 40,
				new Material(ColorAttribute.createDiffuse(Color.CYAN)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates);
//		model = new ObjLoader().loadModel(Gdx.files.internal("teapot.obj"));
		instances = new ArrayList<ModelInstance>();
		for (int i = 0; i < 1; i++) {
			ModelInstance in = new ModelInstance(model);
//			in.materials.get(0).set(ColorAttribute.createDiffuse(Color.RED));
			in.transform.setToTranslation((i / 16) * 160, 0, (i % 16) * 160);
			instances.add(in);
		}

		renderable = new Renderable();
		model.nodes.get(0).parts.get(0).setRenderable(renderable);
		renderable.environment = environment;
//		renderable.primitiveType = GL20.GL_POINTS;
		renderable.worldTransform.idt();

		renderContext = new RenderContext(new DefaultTextureBinder(DefaultTextureBinder.WEIGHTED, 1));
	    // shader = new DefaultShader(renderable);
		shader = new TestShader();
	    shader.init();
	}

	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		timer += delta / 4;
		while (timer >= 1) timer--;

		camController.update();
		cam.position.set(MathUtils.cos(MathUtils.PI2 * timer), 0.5f, MathUtils.sin(MathUtils.PI2 * timer));
		cam.lookAt(0, 0, 0);
		cam.up.set(0, 1, 0);
		cam.update();

		renderContext.begin();
		shader.begin(cam, renderContext);
		shader.render(renderable);
		shader.end();
		renderContext.end();

//		modelBatch.begin(cam);
//		for (int i = 0; i < instances.size(); i++) {
//			modelBatch.render(instances.get(i), shader);
////			instances.get(i).transform.rotate(new Vector3(0, 1, 0), 1);
//		}
//		modelBatch.end();
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
		modelBatch.dispose();
		model.dispose();
		shader.dispose();
	}
}