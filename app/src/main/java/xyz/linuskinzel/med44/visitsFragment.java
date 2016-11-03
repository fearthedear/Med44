package xyz.linuskinzel.med44;


import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class visitsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public visitsFragment() {
        // Required empty public constructor
    }

    public static visitsFragment newInstance() {
        Bundle args = new Bundle();
        visitsFragment fragment = new visitsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    databaseActions dbActions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visits, container, false);


        //fab
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVisit();
            }
        });

        is_empty(view);
        return view;
    }
    public void is_empty(View view) {
        dbActions = new databaseActions(getActivity());
        dbActions.open();
        Cursor cursor = dbActions.getAllVisits();
        ArrayList<String[]> records = new ArrayList<String[]>();

        if (cursor.moveToFirst()) {
            do {
                String[] record = {cursor.getString(0),cursor.getString(1),cursor.getString(2)};
                records.add(record);
            }
            while (cursor.moveToNext());

            mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new visitsAdapter(records);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            View relativeLayout =  view.findViewById(R.id.visitsparent);
            //LinearLayout layout = (LinearLayout) findViewById(R.id.info);

            TextView noConditions = new TextView(getContext());
            String Str = getResources().getString(R.string.noVisits);
            noConditions.setText(Str);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            noConditions.setLayoutParams(params);
            ((RelativeLayout) relativeLayout).addView(noConditions);
        }

        dbActions.close();
    }

    public void addVisit() {
        DialogFragment newFragment = new addVisitFragment();
        newFragment.show(getActivity().getFragmentManager(), "addVisit");
    }
}
