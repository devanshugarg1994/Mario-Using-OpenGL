package Engine;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ComponentSerializerAndDeSerializer implements JsonSerializer<Component>, JsonDeserializer<Component> {

    @Override
    public Component deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            return context.deserialize(element, Class.forName(type));
        } catch ( ClassNotFoundException e) {
            throw  new JsonParseException("Unknown element type : " + type, e);
        }
    }

    @Override
    public JsonElement serialize(Component src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive(src.getClass().getCanonicalName()));
        jsonObject.add("properties", context.serialize(src, src.getClass()));
        return jsonObject;
    }
}
