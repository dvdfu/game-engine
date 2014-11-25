package com.dvdfu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TestShader implements Shader {
	private ShaderProgram program;
	private Camera camera;
	private RenderContext context;
    private int u_projTrans;
    private int u_worldTrans;
    private int u_color;
    private int u_cam;
    private int u_light;
    private int u_lightColor;

	public void init() {
		String vert = Gdx.files.internal("test.vert").readString();
		String frag = Gdx.files.internal("test.frag").readString();
		program = new ShaderProgram(vert, frag);
		if (!program.isCompiled()) throw new GdxRuntimeException(program.getLog());
		u_projTrans = program.getUniformLocation("u_projTrans");
        u_worldTrans = program.getUniformLocation("u_worldTrans");
        u_color = program.getUniformLocation("u_color");
        u_cam = program.getUniformLocation("u_cam");
        u_light = program.getUniformLocation("u_light");
        u_lightColor = program.getUniformLocation("u_lightColor");
	}

	public void dispose() {
		program.dispose();
	}

	public void begin(Camera camera, RenderContext context) {
		this.camera = camera;
        this.context = context;
		program.begin();
        program.setUniformMatrix(u_projTrans, camera.combined);
		context.setDepthTest(GL20.GL_LEQUAL);
        context.setCullFace(GL20.GL_BACK);
	}

	public void render(Renderable renderable) {
		program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
		program.setUniformf(u_color, 0.1f, 0.1f, 0.2f);
		program.setUniformf(u_cam, camera.position);
		program.setUniformf(u_light, 1.5f, 1f, 0.5f);
		program.setUniformf(u_lightColor, 0.2f, 0.6f, 0.7f);
		renderable.mesh.render(program, renderable.primitiveType, renderable.meshPartOffset, renderable.meshPartSize);
	}

	public void end() {
		program.end();
	}

	public int compareTo(Shader other) {
		return 0;
	}

	public boolean canRender(Renderable instance) {
		return true;
	}
}