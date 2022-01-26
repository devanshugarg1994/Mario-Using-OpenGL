package Engine;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene() {
         System.out.println("LevelEditor");
    }


    @Override
    public void update(float dt) {
        if(!changingScene && KeyListner.isKeyPressed(GLFW_KEY_SPACE)) {
            changingScene = true;
        }

        if(changingScene && timeToChangeScene > 0) {
            timeToChangeScene -=dt;
//            Window.get().r -=dt * 5.0f;
//            Window.get().g -=dt * 5.0f;
//            Window.get().b -=dt * 5.0f;

        } else if(changingScene)  {
            Window.changeScene(1);
            Window.get().r = 0.f;
            Window.get().g = 0.f;
            Window.get().b = 0.f;
        }

    }
}
