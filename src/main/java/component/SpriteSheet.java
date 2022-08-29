package component;

import org.joml.Vector2f;
import renderer.Texture;

import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private Texture texture;
    private List<Sprite> sprites;

    public SpriteSheet(Texture texture, int spriteWidth, int spriteHeight, int numberOfSprites, int spacing) {
        this.sprites = new ArrayList<>();
        this.texture = texture;

        int currentX = 0;
        int currentY = (int) (this.texture.getTextureHeight() - spriteHeight);

        // Converting All points in normal Device co-ordinates
        for (int i = 0; i < numberOfSprites; i++) {
            float topY = (currentY + spriteHeight) / this.texture.getTextureHeight();
            float rightX = (currentX + spriteWidth) / this.texture.getTextureWidth();
            float bottomY = currentY / (float) texture.getTextureHeight();
            float leftX = currentX / (float) texture.getTextureWidth();

            Vector2f[] texCords = {
                    new Vector2f(rightX, topY),
                    new Vector2f(rightX, bottomY),
                    new Vector2f(leftX, bottomY),
                    new Vector2f(leftX, topY)
            };

            Sprite sprite = new Sprite();
            sprite.setTexture(this.texture);
            sprite.setTexCord(texCords);
            sprite.setWidth(spriteWidth);
            sprite.setHeight(spriteHeight);
            this.sprites.add(sprite);

            currentX += spriteWidth + spacing;
            if (currentX >= this.texture.getTextureWidth()) {
                currentX = 0;
                currentY -= spriteHeight + spacing;
            }
        }
    }

    public Sprite getSprite(int index) {
        return this.sprites.get(index);
    }

    public int getNumberOfSprites() {
        return this.sprites.size();
    }
}
