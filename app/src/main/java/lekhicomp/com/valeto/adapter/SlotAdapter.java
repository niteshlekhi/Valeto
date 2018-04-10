package lekhicomp.com.valeto.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import lekhicomp.com.valeto.model.SlotDetails;

/**
 * Created by admin on 02-Feb-18.
 */

public class SlotAdapter extends FirebaseRecyclerAdapter<SlotDetails, SlotViewHolder> {

    public SlotAdapter(@NonNull FirebaseRecyclerOptions<SlotDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SlotViewHolder holder, int position, @NonNull SlotDetails model) {

    }

    @Override
    public SlotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
