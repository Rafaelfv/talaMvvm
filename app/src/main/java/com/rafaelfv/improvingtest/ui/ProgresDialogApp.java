package com.rafaelfv.improvingtest.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.rafaelfv.improvingtest.R;
import com.wang.avi.AVLoadingIndicatorView;

public class ProgresDialogApp extends Dialog {

    AVLoadingIndicatorView avi;

    public ProgresDialogApp(@NonNull Context context) {
        super(context);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.setCancelable(false);
        if (savedInstanceState == null) {
            setContentView(R.layout.dialog_progress);
            avi = findViewById(R.id.avi);
        }
    }

    @Override
    public void show() {
        try {
            if (!isShowing()) {
                super.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
