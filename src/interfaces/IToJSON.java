package interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface IToJSON {
    void fromJSON(JSONObject jsonObject) throws JSONException;
}
