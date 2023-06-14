package interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface I_ToJson {
    void fromJSON(JSONObject jsonObject) throws JSONException;
}
