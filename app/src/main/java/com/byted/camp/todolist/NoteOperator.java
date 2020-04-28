package com.byted.camp.todolist;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Update;

import com.byted.camp.todolist.beans.Note;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public interface NoteOperator {

    void deleteNote(Note note);

    void updateNote(Note note);
}
