package renderer;

import javax.xml.transform.Source;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class Shader {

    private int shaderProgramID;
    private String vertexShaderSource;
    private String fragmentShaderSource;
    private String filepath;

    public Shader(String filepath) {
        this.filepath = filepath;
        try {
            String source = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] splitString = source.split("(#type)( )[a-zA-z]+");

            int index = source.indexOf("#type") + 6;
            int eol = source.indexOf("\r\n", index);
            String fristPattern = source.substring(index, eol).trim();

             index = source.indexOf("#type", eol) + 6;
             eol = source.indexOf("\r\n", index);
             String secondPattern = source.substring(index, eol).trim();

            System.out.println(splitString[0]);
            if (fristPattern.equals("vertex")) {
                vertexShaderSource = splitString[1];
            } else if(fristPattern.equals("fragment")) {
                fragmentShaderSource = splitString[1];
            } else {
                throw new IOException("Unexpected Token : " + fristPattern + " ");
            }

            if (secondPattern.equals("vertex")) {
                vertexShaderSource = splitString[2];
            } else if(secondPattern.equals("fragment")) {
                fragmentShaderSource = splitString[2];
            } else  {
                throw new IOException("Unexpected Token : " + secondPattern + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
            assert false: "Error:Could not open shader file: " + filepath + " ";
        }

    }

    public void compile() {
        // ============================================================
        // Compile and link shaders
        // ============================================================

        int vertexID, fragmentID;
        // First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(vertexID, this.vertexShaderSource);
        glCompileShader(vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: " + this.filepath + "\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        // First load and compile the vertex shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(fragmentID, this.fragmentShaderSource);
        glCompileShader(fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: " + this.filepath + "\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        // Link shaders and check for errors
        this.shaderProgramID = glCreateProgram();
        glAttachShader(this.shaderProgramID, vertexID);
        glAttachShader(this.shaderProgramID, fragmentID);
        glLinkProgram(this.shaderProgramID);

        // Check for linking errors
        success = glGetProgrami(this.shaderProgramID, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(this.shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: " + this.filepath + "\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            assert false : "";
        }

    }

    public void use() {
        glUseProgram(this.shaderProgramID);

    }

    public void detach() {
        glUseProgram(0);

    }
}
