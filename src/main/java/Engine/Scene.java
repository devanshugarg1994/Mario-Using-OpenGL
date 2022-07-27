package Engine;

import java.util.ArrayList;
import java.util.List;

public abstract class  Scene {
    private boolean isRunning = false;
    protected Camera camera;
    protected List<GameObject> gameObjects = new ArrayList<>();
    public Scene() {

    }

    public void init() {

    }

    public void start() {
        System.out.println("Starting the Scene");
        for (GameObject object: this.gameObjects) {
            object.start();
        }

        this.isRunning = true;
    }

    public void addGameObjectToScene(GameObject gameObject) {
        if(!this.isRunning) {
            this.gameObjects.add(gameObject);
        } else  {
            this.gameObjects.add(gameObject);
            gameObject.start();
        }
    }

    public Camera getCamera() {
        return this.camera;
    }

    public abstract void update(float dt);
}
