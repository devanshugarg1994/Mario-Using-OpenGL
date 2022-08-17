package Engine;

import imgui.ImGui;
import renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class  Scene {
    private boolean isRunning = false;
    protected Renderer renderer = new Renderer();
    protected Camera camera;
    protected List<GameObject> gameObjects = new ArrayList<>();
    protected GameObject activeGameObject = null; // Parent Game object of the scene
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

    public void sceneImGui() {
        if(this.activeGameObject != null) {
            ImGui.begin("Start Inspector Level");
            this.activeGameObject.imGui();
            ImGui.end();
        }
        this.imGui();
    }

    public void imGui() {

    }
}
