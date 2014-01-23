/******************************************************************************* 
 * Created: Jan 21, 2014
 * 
 * GuideListsDeserializer.java 
 * 
 * Contributors:
 *     Robert Lee - initial API and implementation
 ******************************************************************************/
package com.robleewc.thisguidebook.guidebook;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.robleewc.thisguidebook.models.Guide;
import com.robleewc.thisguidebook.models.GuideList;

import java.lang.reflect.Type;
import java.util.List;


/**
 * @author Robert Lee
 *
 */
public class GuideListsDeserializer implements JsonDeserializer<GuideList> {

    /* (non-Javadoc)
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    @Override
    public GuideList deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        

        JsonObject obj = json.getAsJsonObject();
        GuideList guides = new GuideList();

        // Parse "total" data from
        
        JsonElement total = obj.get("total");
        guides.setTotal(total.getAsInt());

        // Parse "objects" lists from pagination
        Gson dataGson = new Gson();
        Type dataType = new TypeToken<List<Guide>>() {
        }.getType();
        List<Guide> data = dataGson.fromJson(obj.get("data"), dataType);
        guides.setData(data);

        
        return guides;
    }

}
