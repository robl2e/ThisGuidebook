/******************************************************************************* 
 * Created: Jan 21, 2014
 * 
 * GuideItemListAdapter.java 
 * 
 * Contributors:
 *     Robert Lee - initial API and implementation
 ******************************************************************************/

package com.robleewc.thisguidebook.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.robleewc.thisguidebook.R;
import com.robleewc.thisguidebook.models.Guide;

import java.util.List;

/**
 * @author Robert Lee
 */
public class GuideItemListAdapter extends ArrayAdapter<Guide> {

    private LayoutInflater mInflater;

    /**
     * @param context
     * @param resource
     * @param objects
     */
    public GuideItemListAdapter(Context context, int resource, List<Guide> guides) {
        super(context, resource, guides);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.guide_list_item, parent,
                    false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.guide_item_name);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.guide_item_icon);
            convertView.setTag(viewHolder);

        } else {
            // Populate view with saved values from ViewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get object and fill view
        Guide model = getItem(position);
        viewHolder.title.setText(model.getName());

        UrlImageViewHelper.setUrlDrawable(viewHolder.icon, model.getIcon(), R.drawable.ic_launcher);

        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
        public ImageView icon;
    }

}
