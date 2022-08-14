package Engine;

import component.Sprite;
import component.SpriteRenderer;
import component.SpriteSheet;
import org.joml.Vector2f;
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

         this.spriteSheet = AssetsPool.getSpriteSheet("assets/texture/spriteSheet.png");

         this.object1 = new GameObject("mario", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        this.object1.addComponent(new SpriteRenderer(spriteSheet.getSprite(0)));
        this.addGameObjectToScene(this.object1);

        GameObject object2 = new GameObject("goomba", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        object2.addComponent(new SpriteRenderer(spriteSheet.getSprite(10)));
        this.addGameObjectToScene(object2);

    }

    private void loadAllResources() {

        AssetsPool.getShader("assets/shaders/default.glsl");
        AssetsPool.addSpriteSheet("assets/texture/spriteSheet.png",
                new SpriteSheet(AssetsPool.getTexture("assets/texture/spriteSheet.png")
                        , 16,16,24, 0));
    }


    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f ;
    private float spriteFlipTimeLeft = 0.0f;
    @Override
    public void update(float dt) {
        this.spriteFlipTimeLeft-=dt;
        if(this.spriteFlipTimeLeft <= 0) {
            this.spriteFlipTimeLeft = this.spriteFlipTime;
            SpriteRenderer sprite = this.object1.getComponent(SpriteRenderer.class);
            sprite.setSprite(this.spriteSheet.getSprite(this.spriteIndex % 4));
            this.spriteIndex++;
        }

        System.out.println("FPS: " + (1.0f / dt));

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}