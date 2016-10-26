package xyz.linuskinzel.med44;

import android.provider.BaseColumns;

/**
 * Created by linus on 26/10/2016.
 */

public final class med44Contract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private med44Contract() {}

    /* Inner class that defines the table contents */
    public static class vaccines implements BaseColumns {
        public static final String TABLE_NAME = "vaccines";
        public static final String COLUMN_NAME_VACCINE = "vaccine";
        public static final String COLUMN_NAME_DATE_TAKEN = "date_taken";
        public static final String COLUMN_NAME_GOOD_FOR = "good_for"; //means expires at, renew or sth like this
    }

    /* Inner class that defines the table contents */
    public static class prescriptions implements BaseColumns {
        public static final String TABLE_NAME = "prescriptions";
        public static final String COLUMN_NAME_DRUG = "drug";
        public static final String COLUMN_NAME_DATE_PRESCRIBED = "date_prescribed";
        public static final String COLUMN_NAME_TAKE_FOR_HOW_LONG = "take_for_how_long";
        public static final String COLUMN_NAME_HOW_MANY_DAILY = "how_many_daily";
        public static final String COLUMN_NAME_HOW_TAKE_WHEN = "take_when";
    }

    public static class travels implements BaseColumns {
        public static final String TABLE_NAME = "travels";
        public static final String COLUMN_NAME_COUNTRY = "country";
    }

    public static class doc_visits implements BaseColumns {
        public static final String TABLE_NAME = "doc_visits";
        public static final String COLUMN_NAME_DIAGNOSED = "diagnosed";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_IMAGE = "image";
    }

    public static class med_conditions implements BaseColumns {
        public static final String TABLE_NAME = "med_conditions";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE_DIAGNOSED = "date_diagnosed";
    }

}
