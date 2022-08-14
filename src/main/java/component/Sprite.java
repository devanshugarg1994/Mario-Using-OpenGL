package component;

import Engine.Component;
import org.joml.Vector2f;
import renderer.Texture;

public class Sprite {

    private Texture texture;
    private Vector2f [] texCord;
    public Sprite(Texture tex) {
        this.texture = tex;
        Vector2f [] texCord = {
                new Vector2f(1, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 1)
        };
        this.texCord = texCord;
    }

    public Sprite(Texture tex, Vector2f [] texCord) {
        this.texture = tex;
        this.texCord = texCord;
    }

    public Texture getTexture () {
        return this.texture;
    }

    public Vector2f[] getTexCord () {
        return this.texCord;
    }

}
