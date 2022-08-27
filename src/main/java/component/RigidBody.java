package component;

import Engine.Component;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class RigidBody extends Component {
    private int colliderTyoe = 0;
    private float friction = 0;
    public Vector3f velocity = new Vector3f(0, 0.5f, 0);
    public transient Vector4f tmp = new Vector4f(1,1,1,1);
}