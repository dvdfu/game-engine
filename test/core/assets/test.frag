#ifdef GL_ES 
precision mediump float;
#endif
 
uniform vec3 u_color;
uniform vec3 u_cam;
uniform vec3 u_light;
uniform vec3 u_lightColor;
uniform sampler2D u_texture;
uniform sampler2D u_normal;

varying vec3 v_position;
varying vec3 v_normal;
varying vec2 v_texCoord;
 
void main() {
	vec3 color = texture2D(u_texture, v_texCoord).rgb;
	vec3 normal = texture2D(u_normal, v_texCoord).rgb;
	float rimInt = pow(1.0 - dot(normalize(u_cam), normalize(v_normal)), 4.0);

	float dot = dot(normalize(u_light), normalize(v_normal));
	float diffInt = pow(max(dot, 0.0), 2.0);
	float specInt = pow(max(dot, 0.0), 300.0 / length(u_lightColor));
	float shade = pow(max(dot(normalize(u_light), normalize(normal)), 0.0), 0.5);
	
	gl_FragColor = vec4(color * shade + u_lightColor * (diffInt + rimInt) + specInt, 1.0);
}