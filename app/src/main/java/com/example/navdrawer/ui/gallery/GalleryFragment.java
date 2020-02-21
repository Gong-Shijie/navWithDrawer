package com.example.navdrawer.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navdrawer.R;
import com.example.navdrawer.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel = new ViewModelProvider(requireActivity()).get(GalleryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Log.i("gong", "testgit");
        Log.i("gong", "testgit");
        Log.i("gong", "testgit");
       FragmentGalleryBinding binding ;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_gallery,container,false);

        binding.setData(galleryViewModel);
       binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }
}