package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.Provider;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.presentation.ui.event.fights.EventFightsRecyclerViewAdapter;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class EventFightsActivity extends AppCompatActivity implements EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener {

    private static final String ARG_EVENT_ID = "event-id";
    private String mEventId;

    @BindView(R.id.event_fights_list)
    public RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Intent newIntent(Context context, String eventId) {
        Intent intent = new Intent(context, EventFightsActivity.class);
        intent.putExtra(ARG_EVENT_ID, eventId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_fights);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mEventId = bundle.getString(ARG_EVENT_ID);
        }
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new ProviderAsyncTask().execute(Integer.parseInt(mEventId));
    }

    private class ProviderAsyncTask extends AsyncTask<Integer, Void, List<EventFight>> {

        @Override
        protected List<EventFight> doInBackground(Integer... params) {
            try {
                return Provider.getInstance().requestEventFights(params[0]);
            } catch (Exception e) {
                Log.e("EventFightsActivity", "ERROR", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<EventFight> eventList) {
            super.onPostExecute(eventList);

            Log.d("EventFightsActivity", eventList.toString());
            mAdapter = new EventFightsRecyclerViewAdapter(eventList, EventFightsActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onListFragmentInteraction(EventFight item) {

    }
}
