package Engine;

import component.Sprite;
import component.SpriteRenderer;
import org.joml.Vector2f;

public class Prefabs {
    public static GameObject generateSpriteObject(Sprite sprite, float sizeX, float sizeY) {
     GameObject object = new GameObject("Sprite_Gen_OBJECT", new Transform(new Vector2f(), new Vector2f(sizeX, sizeY)), 0);
     SpriteRenderer spriteRenderer = new SpriteRenderer();
     spriteRenderer.setSprite(sprite);
     object.addComponent(spriteRenderer);

     return object;
    }
}