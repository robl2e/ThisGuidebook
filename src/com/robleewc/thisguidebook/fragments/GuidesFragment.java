/*******************************************************************************
 * Created: Jan 21, 2014
 * 
 * GuidesFragment.java 
 * 
 * Contributors:
 *     Robert Lee - initial API and implementation
 ******************************************************************************/

package com.robleewc.thisguidebook.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Response;
import com.robleewc.thisguidebook.R;
import com.robleewc.thisguidebook.guidebook.GuidebookRestClient;
import com.robleewc.thisguidebook.lists.GuideItemListAdapter;
import com.robleewc.thisguidebook.models.Guide;
import com.robleewc.thisguidebook.models.GuideList;

import java.util.List;

/**
 * @author Robert Lee
 */
public class GuidesFragment extends Fragment {

    private final static String LOG_TAG = "GuidesFragment";
    ListView mListView;

    /*
     * (non-Javadoc)
     * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doGetGuides();
    }

    /*
     * (non-Javadoc)
     * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_guides, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);

        return view;
    }

    /******************************************************/
    /***************** CALLBACKS ******************/
    /******************************************************/

    private FutureCallback<Response<GuideList>> mGuideListFC = new FutureCallback<Response<GuideList>>() {
        @Override
        public void onCompleted(Exception e, Response<GuideList> result) {
            if (e != null) {
                Toast.makeText(getActivity(), "Retrive Guides failed", Toast.LENGTH_LONG).show();
                Log.e(LOG_TAG, "Retrive Guides failed" + e.getMessage());
                return;
            }

            String respMsg = result.getHeaders().getResponseMessage();
            int respCode = result.getHeaders().getResponseCode();
            String headers = result.getHeaders().toHeaderString();
            Log.d(LOG_TAG, "headers = " + headers); // debugging

            List<Guide> guideList = result.getResult().getData();
            if (guideList == null) {
                Log.w(LOG_TAG, "Unable to get lists: " + respMsg + " " + respCode);
                return;
            }

            // Update UI
            mListAdapter = buildListData(guideList);
            updateListUI(mListAdapter);
        }

    };
    /******************************************************/
    /******************************************************/
    ListAdapter mListAdapter;

    private ListAdapter buildListData(List<Guide> guides) {
        return new GuideItemListAdapter(getActivity(), 0, guides);
    }

    private void doGetGuides() {
        GuidebookRestClient.getInstance(getActivity()).doGetGuideLists(mGuideListFC, getActivity());
    }

    private void updateListUI(ListAdapter adapt) {
        mListView.setAdapter(adapt);
    }

}
