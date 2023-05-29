package interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface IToJSON {
    JSONObject toJOSN() throws JSONException;
    void fromJSON(JSONObject jsonObject) throws JSONException;
}
