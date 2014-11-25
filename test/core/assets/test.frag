#ifdef GL_ES 
precision mediump float;
#endif
 
uniform vec3 u_color;
uniform vec3 u_cam;
uniform vec3 u_light;
uniform vec3 u_lightColor;

varying vec3 v_position;
varying vec3 v_normal;
 
void main() {
	float rimInt = (pow(dot(u_cam, v_normal), -1.5) - 1.0) / 20.0;
	float dot = dot(normalize(u_light), normalize(v_normal));
	float diffInt = max(dot, 0.0);

	vec3 refl = normalize(reflect(-normalize(u_light),normalize(v_normal)));
	float specInt = pow(max(0.0, dot(normalize(v_normal), refl)), 100.0);
	//gl_FragColor = vec4(u_color + vec3(rimInt), 1.0);
	gl_FragColor = vec4(u_color + u_lightColor * (rimInt + diffInt) + specInt, 1.0);
}