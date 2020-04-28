package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.Date;
import com.byted.camp.todolist.db.TodoContract.ToDoEntry;
import com.byted.camp.todolist.db.TodoDbHelper;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private TodoDbHelper dbHelper;
    private RadioGroup radioGroup;
    private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        dbHelper = new TodoDbHelper(this);

        radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButton = findViewById(R.id.radioButton1);
        radioButton.setChecked(true);
        RadioGroup.OnCheckedChangeListener mylistener = new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup Group, int Checkid) {
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                switch(radioButton.getText().toString().charAt(4)){
                    case 'C':
                        level = 0;
                        break;
                    case 'B':
                        level = 1;
                        break;
                    case 'A':
                        level = 2;
                        break;
                    case 'S':
                        level = 3;
                        break;
                    default: break;
                }
            }
        };
        radioGroup.setOnCheckedChangeListener(mylistener);

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim());
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    private boolean saveNote2Database(String content) {
        // TODO 插入一条新数据，返回是否插入成功
        ContentValues values = new ContentValues();
        values.put(ToDoEntry.CONTENT, content);
        String time = new Date().getTime() + "";
        values.put(ToDoEntry.DATE, time);
        values.put(ToDoEntry.STATE, 0);
        values.put(ToDoEntry.PRIORITY, level);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long new_id = db.insert(ToDoEntry.TABLE_NAME, null, values);

        if (new_id < 0)
            return false;
        else
            return true;
    }
}
