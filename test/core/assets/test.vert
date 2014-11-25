attribute vec3 a_position;
attribute vec3 a_normal;
 
uniform mat4 u_worldTrans;
uniform mat4 u_projTrans;

varying vec3 v_position;
varying vec3 v_normal;
 
void main() {
	v_position = a_position;
	v_normal = a_normal;
    gl_Position = u_projTrans * u_worldTrans * vec4(a_position, 1.0);
}