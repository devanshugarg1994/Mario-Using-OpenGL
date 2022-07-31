package Engine;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private String name;
    private List<Component> components = new ArrayList<>();
    public Transform transform;
    protected boolean firstTime = true;

    public  GameObject(String name) {
        System.out.println("GameObject init");
        this.name = name;
        this.transform = new Transform();
    }

    public  GameObject(String name, Transform transform) {
        System.out.println("GameObject init");
        this.name = name;
        this.transform = transform;
    }

    public <T extends Component> T getComponent(Class<T> componentObject) {
        for (Component c : this.components) {
            if (componentObject.isAssignableFrom(c.getClass())) {
                try {
                    return componentObject.cast(c);

                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting Component";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentObject) {
        for (int i = 0; i < this.components.size(); i++) {
            Component cc = this.components.get(i);
            if (componentObject.isAssignableFrom(cc.getClass())) {
                this.components.remove(i);
                return;
            }
        }

        assert false : "Error: component not found on the GameObject" + componentObject;
    }

    public void addComponent(Component componentObject) {
        this.components.add(componentObject);
        componentObject.gameObject = this;
    }

    public void update(float dt) {
        System.out.println("Updating of Game Object" + this.name);

        if(!this.firstTime) {
            for (int i =0; i < this.components.size(); i++) {
                components.get(i).update(dt);
            }
        }
    }

    public void start() {
        System.out.println("Start of Game Object");
        for (int i=0; i < this.components.size(); i++) {
            this.components.get(i).start();
        }
        this.firstTime = false;
    }

}
