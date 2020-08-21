package com.example.trashapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.trashapplication.R;
import com.example.trashapplication.SellRubbist;
import com.example.trashapplication.notification;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final ImageButton selltrash =root.findViewById(R.id.selltrash);
        final ImageSlider imageSlider=root.findViewById(R.id.slider);
        final ImageButton notif =root.findViewById(R.id.notif);

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), notification.class);
                startActivity(intent);
            }
        });
        selltrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SellRubbist.class);
                startActivity(intent);

            }

        });
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://picsum.photos/id/896/300/200","Advertisement 1"));
        slideModels.add(new SlideModel("https://picsum.photos/id/894/300/200","Advertisement 2"));
        slideModels.add(new SlideModel("https://picsum.photos/id/892/300/200","Advertisement 3"));
        slideModels.add(new SlideModel("https://picsum.photos/id/891/300/200","Advertisement 4"));
        imageSlider.setImageList(slideModels,true);

        return root;
    }
}
