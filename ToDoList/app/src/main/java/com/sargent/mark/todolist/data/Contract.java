package com.sargent.mark.todolist.data;

import android.provider.BaseColumns;

/**
 * Created by mark on 7/4/17.
 */

public class Contract {

    public static class TABLE_TODO implements BaseColumns{
        public static final String TABLE_NAME = "todoitems";

        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DUE_DATE = "duedate";
        // add two columns to data base in order to decide what is the category and which are done
        public static final String COLUMN_NAME_TYPE ="category";
        public static final String COLUMN_NAME_DONE ="done";
    }
}
