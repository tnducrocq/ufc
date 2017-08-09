package fr.tnducrocq.ufc.presentation.ui.main;

import fr.tnducrocq.ufc.data.entity.event.Event;

/**
 * Created by tony on 01/08/2017.
 */

public class EventFragment {

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEventFragmentInteractionListener {
        void onListFragmentInteraction(Event item);
    }

}
