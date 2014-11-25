#ifdef GL_ES 
precision mediump float;
#endif
 
uniform vec3 u_color;
uniform vec3 u_cam;
uniform vec3 u_light;
uniform vec3 u_lightColor;
uniform sampler2D u_texture;

varying vec3 v_position;
varying vec3 v_normal;
varying vec2 v_texCoord;
 
void main() {
	float rimInt = pow(1.0 - dot(u_cam, v_normal), 4.0);

	float dot = dot(normalize(u_light), normalize(v_normal));
	float diffInt = pow(max(dot, 0.0), 2.0);
	float specInt = pow(max(dot, 0.0), 30.0 / length(u_lightColor));
	
	vec3 color = texture2D(u_texture, v_texCoord).rgb * u_color;
	gl_FragColor = vec4(color + u_lightColor * (rimInt + diffInt), 1.0);
}