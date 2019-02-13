package com.anmoworkz.nytimes.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.anmoworkz.nytimes.model.HardCover;
import com.anmoworkz.nytimes.remoterepository.HardCoverbooksRepository;

public class HardCoverViewModel extends ViewModel {
    public MutableLiveData<HardCover> books;
    public HardCoverbooksRepository booksrepository;

    public HardCoverViewModel(){
        booksrepository=new HardCoverbooksRepository();
    }
        public void init(){
        if (this.books!=null){
            return;
        }
        books=booksrepository.getBooks();
    }
    public MutableLiveData<HardCover> getBooks() {
        return this.books;
    }
}
