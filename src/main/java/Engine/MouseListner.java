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
        MouseListner.get().lastX =  MouseListner.get().xPos;
        MouseListner.get().lastY =  MouseListner.get().yPos;
        MouseListner.get().xPos = xpos;
        MouseListner.get().yPos = ypos;
        MouseListner.get().isDragging =  MouseListner.get().mouseButtonPressed[0] ||  MouseListner.get().mouseButtonPressed[1] ||  MouseListner.get().mouseButtonPressed[2];
    }

    // @modifier : check combination like ctrl + "click"
    public static void mouseButtonCallback(long window, int button , int action, int modifier) {
        if( MouseListner.get().mouseButtonPressed.length > action) {
            if(action == GLFW_PRESS) {
                 MouseListner.get().mouseButtonPressed[button] = true;
            } else if(action == GLFW_RELEASE) {
                 MouseListner.get().mouseButtonPressed[button] = false;
                 MouseListner.get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double scrollx, double scrolly) {
         MouseListner.get().scrollX = scrollx;
         MouseListner.get().scrollY = scrolly;
    }

    public static void endFrame() {
         MouseListner.get().scrollX = 0;
         MouseListner.get().scrollY = 0;
         MouseListner.get().lastX =  MouseListner.get().xPos;
         MouseListner.get().lastY =  MouseListner.get().yPos;
//         MouseListner.get().xPos = 0;
//         MouseListner.get().yPos = 0;
    }

    public static float getPosX() {
        return (float) MouseListner.get().xPos;
    }
    public static float getPosY() {
        return (float) MouseListner.get().yPos;
    }
    public static float getDx() {
        return (float)( MouseListner.get().lastX -  MouseListner.get().xPos);
    }
    public static float getDy() {
        return (float) ( MouseListner.get().lastY - MouseListner.get().yPos);
    }

    public static float getScrollX() {
        return (float) ( MouseListner.get().scrollX);
    }

    public static float getScrollY() {
        return (float) ( MouseListner.get().scrollY);
    }

    public static boolean getIsDragging() {
        return ( MouseListner.get().isDragging);
    }

    public static boolean getMouseButtonPressed(int button) {
        if(button <  MouseListner.get().mouseButtonPressed.length) {
          return  MouseListner.get().mouseButtonPressed[button];
        } else  {
            return false;
        }
    }
}
