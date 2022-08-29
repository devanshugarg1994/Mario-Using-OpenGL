package Engine;

import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListner {
    private static double scrollX = 0.0f, scrollY = 0.0f;
    private static double xPos = 0.0f, yPos = 0.0f, lastX = 0.0f, lastY = 0.0f;
    protected static boolean mouseButtonPressed[] = new boolean[3];
    private static boolean isDragging = false;



    public static void mousePositionCallback(long window, double xpos, double ypos) {
        MouseListner.lastX =  MouseListner.xPos;
        MouseListner.lastY =  MouseListner.yPos;
        MouseListner.xPos = xpos;
        MouseListner.yPos = ypos;
        MouseListner.isDragging =  MouseListner.mouseButtonPressed[0] ||  MouseListner.mouseButtonPressed[1] ||  MouseListner.mouseButtonPressed[2];
    }

    // @modifier : check combination like ctrl + "click"
    public static void mouseButtonCallback(long window, int button , int action, int modifier) {
        if( MouseListner.mouseButtonPressed.length > action) {
            if(action == GLFW_PRESS) {
                 MouseListner.mouseButtonPressed[button] = true;
            } else if(action == GLFW_RELEASE) {
                 MouseListner.mouseButtonPressed[button] = false;
                 MouseListner.isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double scrollx, double scrolly) {
         MouseListner.scrollX = scrollx;
         MouseListner.scrollY = scrolly;
    }

    public  void endFrame() {
         MouseListner.scrollX = 0;
         MouseListner.scrollY = 0;
         MouseListner.lastX =  MouseListner.xPos;
         MouseListner.lastY =  MouseListner.yPos;
    }

    public static float getPosX() {
        return (float) MouseListner.xPos;
    }
    public static float getPosY() {
        return (float) MouseListner.yPos;
    }
    public static float getDx() {
        return (float)( MouseListner.lastX -  MouseListner.xPos);
    }
    public static float getDy() {
        return (float) ( MouseListner.lastY - MouseListner.yPos);
    }

    public static float getScrollX() {
        return (float) ( MouseListner.scrollX);
    }

    public static float getScrollY() {
        return (float) ( MouseListner.scrollY);
    }

    public static boolean getIsDragging() {
        return ( MouseListner.isDragging);
    }

    public static float getOrthoX() {
        float currentX = (MouseListner.getPosX() / Window.getWidth()) * 2.0f - 1.0f;
        Vector4f temp = new Vector4f(currentX, 0, 0, 1);
        Camera camera = Window.getScene().camera;

        temp.mul(camera.getInverseProjectionMatrix())
                .mul(camera.getInverseViewMatrix());
        currentX = temp.x;

        return  currentX;
    }

    public static float getOrthoY() {
        float currentY = ((Window.getHeight() - MouseListner.getPosY()) / Window.getHeight()) * 2.0f - 1.0f;
        Vector4f temp = new Vector4f(0, currentY, 0, 1);
        Camera camera = Window.getScene().camera;

        temp.mul(camera.getInverseProjectionMatrix())
                .mul(camera.getInverseViewMatrix());
        currentY = temp.y;

        return  currentY;
    }

    public static boolean getMouseButtonPressed(int button) {
        if(button <  MouseListner.mouseButtonPressed.length) {
          return  MouseListner.mouseButtonPressed[button];
        } else  {
            return false;
        }
    }
}
