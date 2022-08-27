package Engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import component.RigidBody;
import component.Sprite;
import component.SpriteRenderer;
import component.SpriteSheet;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetsPool;


public class LevelEditorScene extends Scene {

    private GameObject object1;
    private SpriteSheet spriteSheet;

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        this.loadAllResources();
        this.camera = new Camera(new Vector2f(-250, 0));
        if(this.levelLoaded) {
            this.activeGameObject = this.gameObjects.get(0);
            System.out.println(this.activeGameObject.getName());
            return;
        }
        this.spriteSheet = AssetsPool.getSpriteSheet("assets/texture/spriteSheet.png");
        this.object1 = new GameObject("object 1", new Transform(new Vector2f(200, 100),
                new Vector2f(256, 256)), 1);
        SpriteRenderer spriteRenderer1 = new SpriteRenderer();
        spriteRenderer1.setColor(new Vector4f(2, 1, 0, 1));
        this.object1.addComponent(spriteRenderer1);
        this.object1.addComponent(new RigidBody());
        this.addGameObjectToScene(this.object1);
        this.activeGameObject = this.object1;

        GameObject object2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100),
                new Vector2f(256, 256)), 2);
        SpriteRenderer spriteRenderer2 = new SpriteRenderer();
        Sprite sprite = new Sprite();
        sprite.setTexture(AssetsPool.getTexture("assets/texture/blendImage2.png"));
        spriteRenderer2.setSprite(sprite);
        object2.addComponent(spriteRenderer2);
        this.addGameObjectToScene(object2);
    }

    private void loadAllResources() {
        AssetsPool.getShader("assets/shaders/default.glsl");
        AssetsPool.addSpriteSheet("assets/texture/spriteSheet.png",
                new SpriteSheet(AssetsPool.getTexture("assets/texture/spriteSheet.png")
                        , 16, 16, 24, 0));
    }


    @Override
    public void update(float dt) {
        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }
        this.renderer.render();
    }

    @Override
    public void imGui() {
        ImGui.begin("Test Window");
        ImGui.text("Some Random text");
        ImGui.end();
    }
}