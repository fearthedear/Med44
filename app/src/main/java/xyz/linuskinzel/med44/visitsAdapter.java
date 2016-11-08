package xyz.linuskinzel.med44;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by linus on 04/11/2016.
 */

public class visitsAdapter extends RecyclerView.Adapter<visitsAdapter.ViewHolder> {
    private ArrayList<String[]> mDataset;
    databaseActions dbActions;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView conditionName;
        public TextView diagnosedDate;
        public ImageView deleteVisit;
        public TextView image1;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
            conditionName = (TextView) v.findViewById(R.id.condition_name);
            diagnosedDate = (TextView) v.findViewById(R.id.diagnosed_date);
            deleteVisit = (ImageView) v.findViewById(R.id.deleteVisit);
            image1 = (TextView) v.findViewById(R.id.imageInput);
        }
    }

    public visitsAdapter(ArrayList myDataset) {
        mDataset = myDataset;
    }

    @Override
    public visitsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visit_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        visitsAdapter.ViewHolder vh = new visitsAdapter.ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(visitsAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        context = holder.conditionName.getContext();
        String[] temp = mDataset.get(position);
        String name = temp[1];
        String diagnosed = temp[0];
        String image1 = temp[2];
        final String visitID = temp[3];


        holder.conditionName.setText(name);
        holder.diagnosedDate.setText("Diagnosed: "+diagnosed);

        holder.deleteVisit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbActions = new databaseActions(context);
                dbActions.open();
                dbActions.deleteVisit(visitID);

                context.startActivity(new Intent(context, MedicalHistory2.class));
            }
        });
        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "This feature is under development. Please check back later!", Toast.LENGTH_LONG).show();
//                visitsFragment visitsFragment = new visitsFragment();
//                visitsFragment.attachImage(visitID);

            }
        });

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

