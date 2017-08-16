package fr.tnducrocq.ufc.presentation;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fr.tnducrocq.ufc.presentation.ui.fighter.details.FighterDetailsFragment;

/**
 * Created by tony on 11/08/2017.
 */

public class FighterActivity extends AppCompatActivity {


    private static final String ARG_FIGHTER_ID = "fighter-id";
    public static final String FIGHTER_DETAILS_TAG = "fighterDetailsTag";

    public static Intent newIntent(Context context, String fighterId) {
        Intent intent = new Intent(context, FighterActivity.class);
        intent.putExtra(ARG_FIGHTER_ID, fighterId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighter);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (savedInstanceState == null) {
            savedInstanceState = getIntent().getExtras();
            setEnterTransition(savedInstanceState);

            Fragment fragment = FighterDetailsFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment, FIGHTER_DETAILS_TAG).commit();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setEnterTransition(Bundle args) {
        if (args != null) {
            /*int res = args.getInt(Constants.EXTRA_TRANSITION_ID, -1);
            if (res != -1) {
                Transition transition = TransitionInflater.from(this).inflateTransition(res);
                getWindow().setEnterTransition(transition);
                transition = TransitionInflater.from(this).inflateTransition(R.transition.details_shared_poster_exit);
                getWindow().setSharedElementReturnTransition(transition);
            }*/
        }
    }


}
