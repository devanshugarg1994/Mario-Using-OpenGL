package Engine;

import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;
import util.AssetsPool;


public class LevelEditorScene extends Scene {

    public LevelEditorScene() {

    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f(-250, 0));

        GameObject object1 = new GameObject("mario", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        object1.addComponent(new SpriteRenderer(AssetsPool.getTexture("assets/texture/test.png")));
        this.addGameObjectToScene(object1);

        GameObject object2 = new GameObject("goomba", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        object2.addComponent(new SpriteRenderer(AssetsPool.getTexture("assets/texture/test2.png")));
        this.addGameObjectToScene(object2);

        this.loadAllResources();
    }

    private void loadAllResources() {
        AssetsPool.getShader("assets/shaders/default.glsl");
    }

    @Override
    public void update(float dt) {
        System.out.println("FPS: " + (1.0f / dt));

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}