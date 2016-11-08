package xyz.linuskinzel.med44;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by linus on 04/11/2016.
 */

public class addVisitFragment extends DialogFragment {
    public interface DialogListener2 {
        public void onDialogPositiveClick(DialogFragment dialog, String condition, String date, String image);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    addVisitFragment.DialogListener2 mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (addVisitFragment.DialogListener2) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add a Visit");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_add_visit, null);

        final EditText diagnosis = (EditText) view.findViewById(R.id.visitDiagnosis);
        final EditText diagnosed_date_input = (EditText) view.findViewById(R.id.diagnosed_date_input);
        //// TODO: 04/11/2016 Process image here
        final TextView image1 = (TextView) view.findViewById(R.id.imageInput);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String condition = diagnosis.getText().toString();
                        String date = diagnosed_date_input.getText().toString();
                        //String image = image1.getText().toString();
                        mListener.onDialogPositiveClick(addVisitFragment.this, condition, date, "empty");
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mListener.onDialogNegativeClick(addVisitFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
