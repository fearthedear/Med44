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
import android.widget.NumberPicker;

/**
 * Created by linus on 02/11/2016.
 */

public class addPrescriptionFragment extends DialogFragment implements NumberPicker.OnValueChangeListener {
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        //get new value and convert it to String
        //if you want to use variable value elsewhere, declare it as a field
        //of your main function
        String value = "" + newVal;
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String drug, int perday, int fordays);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    DialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
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
        builder.setTitle("Add a Prescription");
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_add_prescription, null);

        final EditText druginput = (EditText) view.findViewById(R.id.drugInput);
        final NumberPicker perday = (NumberPicker) view.findViewById(R.id.perday);
        final NumberPicker fordays = (NumberPicker) view.findViewById(R.id.fordays);
        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        perday.setMinValue(1);
        fordays.setMinValue(1);
        //Specify the maximum value/number of NumberPicker
        perday.setMaxValue(5);
        fordays.setMaxValue(365);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        perday.setWrapSelectorWheel(true);

        //Set a value change listener for NumberPicker
        perday.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //int perday = newVal;
            }
        });
        fordays.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //int fordays = newVal;
            }
        });

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int takedays = perday.getValue();
                        int forrdays = fordays.getValue();
                        String drug = druginput.toString();
                        mListener.onDialogPositiveClick(addPrescriptionFragment.this, drug, takedays, forrdays);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        mListener.onDialogNegativeClick(addPrescriptionFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
