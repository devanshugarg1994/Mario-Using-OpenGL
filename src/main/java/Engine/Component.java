package Engine;

public abstract class Component {

    public GameObject gameObject = null;

    public abstract void start();
    public  void update(float dt) {

    }

    public void imGui() {

    }
}
