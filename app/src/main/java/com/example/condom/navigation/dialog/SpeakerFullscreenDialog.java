package com.example.condom.navigation.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.condom.R;
import com.example.condom.adapters.FullScreenSpeakerAdapter;
import com.example.condom.modelItem.SpeakersCardsItem;

import java.util.ArrayList;

public class SpeakerFullscreenDialog extends DialogFragment implements View.OnClickListener {
    private ArrayList<SpeakersCardsItem> fullScreenCardItems = new ArrayList<>();
    static private ArrayList<SpeakersCardsItem> speakersCardsItems = new ArrayList<>();


    public static SpeakerFullscreenDialog newInstance(SpeakersCardsItem speakersCardsItem){
        speakersCardsItems.add(speakersCardsItem);
        return new SpeakerFullscreenDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fs_dialog_speaker, container, false);
        ImageButton close = view.findViewById(R.id.fullscreen_close);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFullScreen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new FullScreenSpeakerAdapter(fullScreenCardItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SpeakersCardsItem speakersCardInfo = speakersCardsItems.get(0);
        close.setOnClickListener(this);
        fullScreenCardItems.add(new SpeakersCardsItem(speakersCardInfo.getKeyId(), speakersCardInfo.getSpeakerName(), speakersCardInfo.getSpeakerImage(), speakersCardInfo.getSpeakerProfession(),
                speakersCardInfo.getSpeakerDescription(), speakersCardInfo.getSpeakerPerfTime(), speakersCardInfo.getSpeakerPlace(), speakersCardInfo.getSpeakerPerf()));
        speakersCardsItems.clear();
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
