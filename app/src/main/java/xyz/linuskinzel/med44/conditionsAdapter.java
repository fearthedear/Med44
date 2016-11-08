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

import java.util.ArrayList;


/**
 * Created by linus on 03/11/2016.
 */

public class conditionsAdapter extends RecyclerView.Adapter<conditionsAdapter.ViewHolder> {
    private ArrayList<String[]> mDataset;
    databaseActions dbActions;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView conditionName;
        public TextView diagnosedDate;
        public ImageView deleteCondition;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
            conditionName = (TextView) v.findViewById(R.id.condition_name);
            diagnosedDate = (TextView) v.findViewById(R.id.diagnosed_date);
            deleteCondition = (ImageView) v.findViewById(R.id.deleteCondition);
        }
    }

    public conditionsAdapter(ArrayList myDataset) {
        mDataset = myDataset;
    }

    @Override
    public conditionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.condition_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(conditionsAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        context = holder.conditionName.getContext();
        String[] temp = mDataset.get(position);
        String name = temp[0];
        String diagnosed = temp[1];
        final String conditionID = temp[2];

        holder.conditionName.setText(name);
        holder.diagnosedDate.setText("Diagnosed: "+diagnosed);

        holder.deleteCondition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbActions = new databaseActions(context);
                dbActions.open();
                dbActions.deleteCondition(conditionID);

                context.startActivity(new Intent(context, MedicalHistory2.class));
            }
        });

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
