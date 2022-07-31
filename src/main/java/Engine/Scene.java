package Engine;

import renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class  Scene {
    private boolean isRunning = false;
    protected Renderer renderer = new Renderer();
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
            this.renderer.add(object);
        }

        this.isRunning = true;
    }

    public void addGameObjectToScene(GameObject gameObject) {
        if(!this.isRunning) {
            this.gameObjects.add(gameObject);
        } else  {
            this.gameObjects.add(gameObject);
            gameObject.start();
            this.renderer.add(gameObject);
        }
    }

    public Camera getCamera() {
        return this.camera;
    }

    public abstract void update(float dt);
}
