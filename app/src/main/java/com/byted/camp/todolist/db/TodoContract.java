package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoEntry.TABLE_NAME + " (" +
                    ToDoEntry._ID + " INTEGER PRIMARY KEY," +
                    ToDoEntry.CONTENT + " TEXT," +
                    ToDoEntry.DATE + " TEXT," +
                    ToDoEntry.STATE + " INTEGER," +
                    ToDoEntry.PRIORITY + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ToDoEntry.TABLE_NAME;

    public static class ToDoEntry implements BaseColumns {

        public static final String TABLE_NAME = "mylist";

        public static final String CONTENT = "content";

        public static final String DATE = "date";

        public static final String STATE = "done";

        public static final String PRIORITY = "priority";
    }

    private TodoContract() {
    }

}
