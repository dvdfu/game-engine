package com.dvdfu.game.handlers;

import com.dvdfu.game.objects.GameObject;

public class ObjectPool {

	public ObjectPool(final GameStage stage) {
		// solid = new Pool<Floor>() {
		// protected Floor newObject() {
		// return new Floor(stage);
		// }
		// }
	}

	// public Floor getSolid() {
	// return solid.obtain();
	// }

	public void free(GameObject object) {
		// if (object instanceof Floor) {
		// solid.free((Floor) object);
		// }
	}
}
