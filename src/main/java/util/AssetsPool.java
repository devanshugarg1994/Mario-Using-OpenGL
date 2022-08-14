package util;

import component.Sprite;
import component.SpriteSheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetsPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, SpriteSheet> spriteSheets = new HashMap<>();

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

    public static void addSpriteSheet(String resourceName, SpriteSheet spriteSheet) {
        File file = new File(resourceName);
        if(!AssetsPool.spriteSheets.containsKey(file.getAbsolutePath())) {
            AssetsPool.spriteSheets.put(file.getAbsolutePath(), spriteSheet);
        }
    }

    public static SpriteSheet getSpriteSheet(String resourceName) {
        File file = new File(resourceName);

        if(!AssetsPool.spriteSheets.containsKey(file.getAbsolutePath())) {
            assert false: "Error: AssetsPool::getSpriteSheet - Tried to access a spriteSheet " + resourceName +
                    " which has not been added";
        }

        return AssetsPool.spriteSheets.getOrDefault(file.getAbsolutePath(), null);
    }
}
