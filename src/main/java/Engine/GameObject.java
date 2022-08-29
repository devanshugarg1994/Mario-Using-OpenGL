package Engine;

import component.Component;
import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private int uUid = -1;
    public static int ID_COUNTER = 0;

    private String name;
    private List<Component> components = new ArrayList<>();
    public Transform transform;
    protected boolean firstTime = true;
    private int zIndex;


    public  GameObject(String name, Transform transform, int zIndex) {
        System.out.println("GameObject init");
        this.name = name;
        this.transform = transform;
        this.zIndex = zIndex;
        this.uUid = ID_COUNTER++;
    }

    public int getzIndex() {
        return this.zIndex;
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
        componentObject.generateID();
        this.components.add(componentObject);
        componentObject.gameObject = this;
    }

    public void update(float dt) {
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

    public void imGui() {
        for(Component c : this.components) {
            c.imGui();
        }
    }

    public String getName() {
        return this.name;
    }

    public static void initUuid(int maxId) {
        ID_COUNTER = maxId;
    }

    public int getUuid() {
        if(this.uUid == -1) {
            assert false: "Try to accessing Uuid which is not set : " + this;
        }
        return this.uUid;
    }

    public  List<Component> getAllComponents() {
        return this.components;
    }
}
