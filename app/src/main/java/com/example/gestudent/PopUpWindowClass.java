package com.example.gestudent;

import android.content.Context;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class PopUpWindowClass extends PopupWindow {

    public PopUpWindowClass( Context context){
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.popup,null);
        setContentView(view);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);


        showAtLocation(view, Gravity.CENTER,0,0);


    }

}
