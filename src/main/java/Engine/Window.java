package Engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import scenes.LevelEditorScene;
import scenes.Scene;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private static Window window;
    private long glfwWindow;
    private static Scene currentScene;
    public float r = 1.0f;
    public float g = 1.0f;
    public float b = 1.0f;
    public float a = 1.0f;

    private ImGuiLayer imGuiLayer;


    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
    }

    public static void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene = new LevelEditorScene();
                break;
            case 1:
                currentScene = new LevelScene();
            default:
                assert false : "Unknown Scene" + "  " + newScene + " ";
                break;
        }

        Window.currentScene.load();
        currentScene.init();
        currentScene.start();
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public static Scene getScene() {
        return Window.currentScene;
    }

    public void run() {
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");

        this.init();
        this.loop();

        // Free Memory
        glfwFreeCallbacks(this.glfwWindow);
        glfwDestroyWindow(this.glfwWindow);

        //Terminate GLFW and remove all callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {

        //setUp Error Callback
        GLFWErrorCallback.createPrint(System.err).set();

        //init GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to Init GLFW");
        }

        // configure
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //create the window

        this.glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (this.glfwWindow == NULL) {
            throw new IllegalStateException("Failed Tom Create GLFW window");
        }

        // MouseEvent Callback
        glfwSetCursorPosCallback(this.glfwWindow, MouseListner::mousePositionCallback);
        glfwSetMouseButtonCallback(this.glfwWindow, MouseListner::mouseButtonCallback);
        glfwSetScrollCallback(this.glfwWindow, MouseListner::mouseScrollCallback);
        glfwSetKeyCallback(this.glfwWindow, KeyListner::keyCallback);
        glfwSetWindowSizeCallback(this.glfwWindow, (w, newWidth, newHeight) -> {
            Window.setWidth(newWidth);
            Window.setHeight(newHeight);
        });


        // Make OpenGL context current
        glfwMakeContextCurrent(this.glfwWindow);
        glfwSwapInterval(1);
        glfwShowWindow(this.glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
        this.imGuiLayer = new ImGuiLayer(this.glfwWindow);
        this.imGuiLayer.initImGui();
        Window.changeScene(0);
    }

    public void loop() {
        float beginTime = (float) glfwGetTime();
        float endTime;
        float dt = -1.0f;
        while (!glfwWindowShouldClose(this.glfwWindow)) {
            // poll events
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt >= 0) {
                currentScene.update(dt);
            }
            this.imGuiLayer.update(dt, currentScene);
            glfwSwapBuffers(this.glfwWindow);
            endTime = (float) glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
        Window.currentScene.saveAndExit();
    }

    public static float getWidth() {
        return Window.get().width;
    }

    public static float getHeight() {
        return Window.get().height;
    }

    public static void setWidth(int width) {
        Window.get().width = width;
    }
    public static void setHeight(int height) {
        Window.get().height = height;
    }

}
