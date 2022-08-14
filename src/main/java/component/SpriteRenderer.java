package component;

import Engine.Component;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {


    private Vector4f color;
    private Sprite sprite;

    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);
    }
    //  * (0, 1)   *(1, 1)
    //  * (0, 0)   *(1, 0)
    public Vector2f[] getTextCords() {
      return   this.sprite.getTexCord();
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float dt) {


    }

    public Vector4f getColor() {
        return this.color;
    }

    public Texture getTexture() {
        return this.sprite.getTexture();
    }
}
