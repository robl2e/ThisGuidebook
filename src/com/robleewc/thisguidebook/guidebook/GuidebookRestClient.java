
package com.robleewc.thisguidebook.guidebook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.robleewc.thisguidebook.models.GuideList;

/**
 * @author Robert Lee
 */
public class GuidebookRestClient {

    private final String LOG_TAG = "GuidebookRestClient";

    /* Headers */

    // add header client - CLIENT_HEADER_NAME, CLIENT_HEADER_VALUE

    // add header version - "accept", API_VERSION

    /* Endpoints */

    protected static final String BASE_URL = "http://guidebook.com/";
    public static final String API_UPCOMING_GUIDES = "http://guidebook.com/service/v2/upcomingGuides/";

    private static GuidebookRestClient singleton;

    private GuidebookRestClient(Context ctx) {
    }

    public static synchronized GuidebookRestClient getInstance(Context ctx) {
        if (singleton == null) {
            singleton = new GuidebookRestClient(ctx);
        }
        return singleton;
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private Gson getGuideListsTypeAdapter() {
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(GuideList.class, new GuideListsDeserializer());
        Gson gson = gBuilder.create();
        return gson;
    }

    public void doGetGuideLists(FutureCallback<Response<GuideList>> future, Context ctx) {
        Gson gson = getGuideListsTypeAdapter();
        Ion.getDefault(ctx).configure().setGson(gson);
        Ion.with(ctx, API_UPCOMING_GUIDES)
                .setLogging(LOG_TAG, Log.DEBUG)
                .as(new TypeToken<GuideList>() {
                })
                .withResponse()
                .setCallback(future);

    }

}
