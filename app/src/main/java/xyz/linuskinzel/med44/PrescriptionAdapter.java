package xyz.linuskinzel.med44;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by linus on 02/11/2016.
 */

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.ViewHolder> {
    private ArrayList<String[]> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCardView;
        public TextView drugnametextview;
        public TextView forDaystextview;
        public TextView perdaystextview;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
            drugnametextview = (TextView) v.findViewById(R.id.drug123);
            forDaystextview = (TextView) v.findViewById(R.id.fordays);
            perdaystextview = (TextView) v.findViewById(R.id.howmany);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PrescriptionAdapter(ArrayList myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PrescriptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prescription_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String[] temp = mDataset.get(position);
        String drug = temp[0];
        String fordays = temp[2];
        String perday = temp[1];
        holder.drugnametextview.setText(drug);
        holder.forDaystextview.setText("Take the drug for "+fordays+" days.");
        holder.perdaystextview.setText("Take "+perday+" pills per day.");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


