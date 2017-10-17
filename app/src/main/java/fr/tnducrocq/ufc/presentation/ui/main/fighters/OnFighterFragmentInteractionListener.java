package fr.tnducrocq.ufc.presentation.ui.main.fighters;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;

/**
 * Created by tony on 16/10/2017.
 */

public interface OnFighterFragmentInteractionListener {
    void onSeeAllFragmentInteraction(WeightCategory category);

    void onListFragmentInteraction(Fighter item);
}