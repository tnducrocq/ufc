package fr.tnducrocq.ufc.presentation.ui.fighter.details;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 11/08/2017.
 */

public class FighterDetailsFragment extends Fragment {

    protected Unbinder unbinder;

    @BindView(R.id.imageView2)
    ImageView imageView2;


    public static FighterDetailsFragment newInstance() {
        FighterDetailsFragment fragment = new FighterDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fighter_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        final Matrix matrix = imageView2.getImageMatrix();
        final float imageWidth = imageView2.getDrawable().getIntrinsicWidth();
        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        final float scaleRatio = screenWidth / imageWidth;
        matrix.postScale(scaleRatio, scaleRatio);
        imageView2.setImageMatrix(matrix);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
