package com.viacom.viacompt.util;

import com.google.gson.Gson;
import com.viacom.viacompt.models.ApiResponse;

import static com.viacom.viacompt.util.LogUtils.*;

/**
 * Created by Shilpan Patel on 2/2/15.
 */
public class JsonUtils {

    private static final String TAG = makeLogTag("JsonUtils");

    public static ApiResponse parseJsonToApiResponse(String json) {
        Gson gson = new Gson();
        ApiResponse apiResponse = gson.fromJson(json, ApiResponse.class);
        LOGD(TAG, "Success : " + apiResponse.getSuccess());
        return apiResponse;
    }
}
