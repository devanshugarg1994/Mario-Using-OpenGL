package Engine;

public abstract class Component {

    public GameObject gameObject = null;

    protected boolean firstTime = true;

    public abstract void start();
    public abstract void update(float dt);
}
