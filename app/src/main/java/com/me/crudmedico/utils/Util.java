package com.me.crudmedico.utils;

import android.widget.EditText;
import android.widget.TextView;

public class Util {
    public static boolean textviewNotEmpty(TextView textView) {
        return !(textView.getText().toString().equals(""));
    }

    public static boolean edittextNotEmpty(EditText editText) {
        return !(editText.getText().toString().equals(""));
    }
}
