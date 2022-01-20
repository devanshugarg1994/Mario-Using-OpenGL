package Engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
   private int width, height;
   private String title;
   private static Window window;
   private long glfwWindow;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
    }

    public static Window get() {
        if(Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run() {
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");

       this.init();
       this.loop();
    }

    public void init() {

        //setUp Error Callback
        GLFWErrorCallback.createPrint(System.err).set();

        //init GLFW
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to Init GLFW");
        }

        // configure
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //create the window

        this.glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(this.glfwWindow == NULL) {
            throw new IllegalStateException("Failed Tom Create GLFW window");
        }

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


    }

    public void loop() {

        while (!glfwWindowShouldClose(this.glfwWindow)) {
            // poll events
            glfwPollEvents();

            glClearColor(1.0f, 0.f, 0.f, 1.0f);

            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(this.glfwWindow);
        }

    }



}
