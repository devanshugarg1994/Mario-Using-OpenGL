package scenes;

import Engine.Camera;
import Engine.GameObject;
import Engine.Prefabs;
import Engine.Transform;
import component.*;
import imgui.ImGui;
import imgui.ImVec2;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetsPool;


public class LevelEditorScene extends Scene {

    private GameObject object1;
    private SpriteSheet spriteSheet;

    MouseControl mouseControl = new MouseControl();
    public LevelEditorScene() {

    }

    @Override
    public void init() {
        this.loadAllResources();
        this.camera = new Camera(new Vector2f(-250, 0));
        this.spriteSheet = AssetsPool.getSpriteSheet("assets/texture/spriteSheets/decorationsAndBlocks.png");

        if(this.levelLoaded) {
            this.activeGameObject = this.gameObjects.get(0);
            System.out.println(this.activeGameObject.getName());
            return;
        }
        this.object1 = new GameObject("object 1", new Transform(new Vector2f(200, 100),
                new Vector2f(256, 256)), 1);
        SpriteRenderer spriteRenderer1 = new SpriteRenderer();
        spriteRenderer1.setColor(new Vector4f(2, 1, 0, 1));
        this.object1.addComponent(spriteRenderer1);
        this.object1.addComponent(new RigidBody());
        this.addGameObjectToScene(this.object1);
        this.activeGameObject = this.object1;

    }

    private void loadAllResources() {
        AssetsPool.getShader("assets/shaders/default.glsl");
        AssetsPool.addSpriteSheet("assets/texture/spriteSheets/decorationsAndBlocks.png",
                new SpriteSheet(AssetsPool.getTexture("assets/texture/spriteSheets/decorationsAndBlocks.png")
                        , 16, 16, 81, 0));
    }


    @Override
    public void update(float dt) {
        this.mouseControl.update(dt);
        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }
        this.renderer.render();
    }

    @Override
    public void imGui() {
        ImGui.begin("Test Window");
        ImVec2  windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);
        float windowEndPointX = windowPos.x + windowSize.x;

        for (int i= 0; i< this.spriteSheet.getNumberOfSprites(); i++) {
            Sprite sprite = this.spriteSheet.getSprite(i);
            float spriteWidth = sprite.getWidth() *  4;
            float spriteHeight = sprite.getHeight() * 4;

            int id = sprite.getTextureID();
            Vector2f[] textCords = sprite.getTexCord();

            ImGui.pushID(i);
            if(ImGui.imageButton(id, spriteWidth, spriteHeight, textCords[0].x, textCords[0].y, textCords[2].x, textCords[2].y)) {
                GameObject object = Prefabs.generateSpriteObject(sprite, spriteWidth, spriteHeight);
                this.mouseControl.pickUpObject(object);
            }
            ImGui.popID();
            ImVec2 lastButtonPos = new ImVec2();
            ImGui.getItemRectMax(lastButtonPos);
            float lastButtonX2 = lastButtonPos.x;
            float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;

            if((i + 1 < this.spriteSheet.getNumberOfSprites()) && (nextButtonX2 < windowEndPointX)) {
                ImGui.sameLine();
            }
        }
        ImGui.end();
    }


}