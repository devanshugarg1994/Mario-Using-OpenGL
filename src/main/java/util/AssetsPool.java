package util;

import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetsPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();

    public  static Shader getShader(String filepath) {
        File file = new File(filepath);
        if(AssetsPool.shaders.containsKey(file.getAbsolutePath())) {
            return AssetsPool.shaders.get(file.getAbsolutePath());
        } else {
            Shader shader = new Shader(filepath);
            AssetsPool.shaders.put(file.getAbsolutePath(), shader);
            shader.compile();
            return  shader;
        }
    }

    public static Texture getTexture(String filepath) {
        File file  = new File(filepath);
        if(AssetsPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetsPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture(filepath);
            AssetsPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }
}
