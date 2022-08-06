package Engine;

import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {


    private Vector4f color;
    private Vector2f [] texCords;
    private Texture texture;

    public SpriteRenderer(Texture texture) {
        this.texture = texture;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.texture = null;
    }
    //  * (0, 1)   *(1, 1)
    //  * (0, 0)   *(1, 0)
    public Vector2f[] getTextCords() {
        Vector2f [] texCords = {
                new Vector2f(1, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 1)
        };
        return texCords;
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
        return this.texture;
    }
}
