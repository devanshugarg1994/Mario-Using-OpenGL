package Engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import imgui.ImGui;
import renderer.Renderer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class  Scene {
    private boolean isRunning = false;
    protected boolean levelLoaded = false;
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

    public void saveAndExit() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentSerializerAndDeSerializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();

        try {
            FileWriter writer = new FileWriter("level.txt");
            writer.write(gson.toJson(this.gameObjects));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Component.class, new ComponentSerializerAndDeSerializer())
                .registerTypeAdapter(GameObject.class, new GameObjectDeserializer())
                .create();

        String inFile = "";
        try {
            inFile = new String(Files.readAllBytes(Paths.get("level.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!inFile.equals("")) {
            GameObject [] objs = gson.fromJson(inFile, GameObject[].class);
            for (int i =0; i < objs.length;i++) {
                addGameObjectToScene(objs[i]);
            }
            this.levelLoaded = true;
        }
    }
}
