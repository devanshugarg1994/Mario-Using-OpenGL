package Engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListner {
    private static MouseListner instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    protected boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private  MouseListner() {
        this.scrollX = 0.0f;
        this.scrollY = 0.0f;
        this.xPos = 0.0f;
        this.yPos = 0.0f;
        this.lastX = 0.0f;
        this.lastY = 0.0f;

    }

    public static MouseListner get() {
        if(MouseListner.instance == null) {
            MouseListner.instance = new MouseListner();
        }
        return MouseListner.instance;
    }

    public static void mousePositionCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    // @modifier : check combination like ctrl + "click"
    public static void mouseButtonCallback(long window, int button , int action, int modifier) {
        if(get().mouseButtonPressed.length > action) {
            if(action == GLFW_PRESS) {
                get().mouseButtonPressed[button] = true;
            } else if(action == GLFW_RELEASE) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double scrollx, double scrolly) {
        get().scrollX = scrollx;
        get().scrollY = scrolly;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
//        get().xPos = 0;
//        get().yPos = 0;
    }

    public static float getPosX() {
        return (float)get().xPos;
    }
    public static float getPosY() {
        return (float)get().yPos;
    }
    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }
    public static float getDy() {
        return (float) (get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float) (get().scrollX);
    }

    public static float getScrollY() {
        return (float) (get().scrollY);
    }

    public static boolean getIsDragging() {
        return (get().isDragging);
    }

    public static boolean getMouseButtonPressed(int button) {
        if(button < get().mouseButtonPressed.length) {
          return get().mouseButtonPressed[button];
        } else  {
            return false;
        }
    }
}
