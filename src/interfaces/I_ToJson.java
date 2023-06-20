package interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Interfaz para crear instancias de clases a partir de un json
 */
public interface I_ToJson {
    void fromJSON(JSONObject jsonObject) throws JSONException;
}
