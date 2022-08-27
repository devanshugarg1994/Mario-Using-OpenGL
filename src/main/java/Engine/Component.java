package Engine;

import imgui.ImGui;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class Component {

    public transient GameObject gameObject = null;

    public void start() {

    }

    public void update(float dt) {

    }

    public void imGui() {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
               if(Modifier.isTransient(field.getModifiers())) {
                   continue;
               }
                boolean isPrivate = Modifier.isPrivate(field.getModifiers());
                if (isPrivate) {
                    field.setAccessible(true);
                }
                Class type = field.getType();
                Object value = field.get(this);
                String name = field.getName();

                if (type == int.class) {
                    int val = (int) value;
                    int imGuiVal[] = {val};
                    if (ImGui.dragInt("name", imGuiVal)) {
                        field.set(this, imGuiVal[0]);
                    }

                } else if (type == float.class) {
                    float val = (float) value;
                    float imGuiVal[] = {val};
                    if (ImGui.dragFloat(field.getName(), imGuiVal)) {
                        field.set(this, imGuiVal[0]);
                    }
                }else if (type == boolean.class) {
                    boolean val = (boolean) value;
                    if (ImGui.checkbox(field.getName(), val)) {
                        field.set(this, !val);
                    }

                }else if (type == Vector3f.class) {
                    Vector3f val = (Vector3f) value;
                    float[] imVec = {val.x, val.y, val.z};
                    if (ImGui.dragFloat3(field.getName(), imVec)) {
                        val.set(imVec[0], imVec[1], imVec[2]);
                    }
                }else if (type == Vector4f.class) {
                    Vector4f val = (Vector4f) value;
                    float[] imVec = {val.x, val.y, val.z, val.w};
                    if (ImGui.dragFloat4(field.getName(), imVec)) {
                        val.set(imVec[0], imVec[1], imVec[2], imVec[3]);
                    }
                }

                if (isPrivate) {
                    field.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
