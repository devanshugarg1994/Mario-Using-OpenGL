package component;

import Engine.GameObject;
import Engine.MouseListner;
import Engine.Window;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class MouseControl extends Component{
    GameObject holdingObject = null;


    public void pickUpObject(GameObject object) {
        this.holdingObject = object;
        Window.getScene().addGameObjectToScene(object);
    }

    public void objectPlaced() {
        this.holdingObject = null;
    }

    @Override
    public void update(float dt) {
        if(this.holdingObject != null) {
            this.holdingObject.transform.position.x = MouseListner.getOrthoX();
            this.holdingObject.transform.position.y = MouseListner.getOrthoY();

            if(MouseListner.getMouseButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                this.objectPlaced();
            }
        }
    }
}