package Engine;

import org.joml.Vector2f;

public class Transform {
    public Vector2f position;
    public Vector2f scale;

    public Transform() {
        this.init(new Vector2f(), new Vector2f());
    }

    public Transform(Vector2f position) {
        this.init(position, new Vector2f());
    }

    public Transform(Vector2f position, Vector2f scale) {
        this.init(position, scale);
    }


    private void init(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
    }
}
