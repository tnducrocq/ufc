package fr.tnducrocq.ufc.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.presentation.ui.view.CardPieView;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.fighter_kick_card)
    public CardPieView kickView;

    @BindView(R.id.fighter_grappling_card)
    public CardPieView grapplingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        kickView.init(993, 473);
        grapplingView.init(8, 5);
    }
}
