#ifdef GL_ES 
precision mediump float;
#endif
 
uniform vec3 u_color;
uniform vec3 u_cam;
uniform vec3 u_light;

varying vec3 v_position;
varying vec3 v_normal;
 
void main() {
	//gl_FragColor = vec4((v_normal + vec3(1.0)) / 2.0, 1.0);
	float intensity = (pow(dot(u_cam, v_normal), -1.25) - 1.0) / 40.0;
	vec3 light = u_light;
	gl_FragColor = vec4(u_color + vec3(intensity), 1.0);
	//gl_FragColor = vec4(light, 1.0);
}