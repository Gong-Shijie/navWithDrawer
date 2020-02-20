package com.example.navdrawer.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {


   public MutableLiveData<String> theText=new MutableLiveData<>();

    public MutableLiveData<String> gettheText() {
        if(theText ==null){
            theText = new MutableLiveData<>();
            theText.setValue("This is gallery fragment use databinding");
        }
        return theText;

    }

    public void settheText(MutableLiveData<String> theText) {

        this.theText = theText;
    }

    public GalleryViewModel() {

        theText.setValue("This is gallery fragment use databinding");
    }



}