package com.example.condom.navigation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.condom.R;

public class CardFullscreenDialog extends DialogFragment implements View.OnClickListener {

    public static CardFullscreenDialog newInstance(){
        return new CardFullscreenDialog();
    }

    /*@Nullable
    public View onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_screen_card_dialog, container, false);

        ImageButton close = view.findViewById(R.id.fullscreen_close);
        ImageButton action = view.findViewById(R.id.fullscreen_fav);

        close.setOnClickListener(this);
        action.setOnClickListener(this);

        return view;
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_screen_card_dialog, container, false);

        ImageButton close = view.findViewById(R.id.fullscreen_close);
        ImageButton action = view.findViewById(R.id.fullscreen_fav);

        close.setOnClickListener(this);
        action.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fullscreen_close:
                dismiss();
                break;

            case R.id.fullscreen_fav:

                break;
        }
    }
}
