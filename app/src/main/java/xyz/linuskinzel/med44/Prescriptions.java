package xyz.linuskinzel.med44;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Prescriptions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);

        dbActions = new databaseActions(this);

        is_empty();
    }

    databaseActions dbActions;

    public void is_empty() {
        dbActions = new databaseActions(this);
        dbActions.open();
        Cursor cursor = dbActions.getAllPrescriptionRecords();

        if (cursor.moveToFirst());
        else {
            View relativeLayout =  findViewById(R.id.activity_prescriptions);
            //LinearLayout layout = (LinearLayout) findViewById(R.id.info);

            TextView noPrescriptions = new TextView(this);
            String Str = this.getString(R.string.noPrescriptions);
            noPrescriptions.setText(Str);
            noPrescriptions.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            ((RelativeLayout) relativeLayout).addView(noPrescriptions);
        }

        dbActions.close();
    }
}
