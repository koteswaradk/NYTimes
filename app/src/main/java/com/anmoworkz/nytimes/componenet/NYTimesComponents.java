package com.anmoworkz.nytimes.componenet;

import com.anmoworkz.nytimes.model.Book;
import com.anmoworkz.nytimes.model.BuyLink;
import com.anmoworkz.nytimes.model.HardCover;
import com.anmoworkz.nytimes.model.Isbn;
import com.anmoworkz.nytimes.model.Results;
import com.anmoworkz.nytimes.remoterepository.HardCoverbooksRepository;
import com.anmoworkz.nytimes.view.MainActivity;

import dagger.Component;

@Component(modules = {HardCover.class,Book.class,BuyLink.class,Isbn.class,Results.class})
public interface NYTimesComponents {
    public void inject(MainActivity mainActivity);
   // HardCoverbooksRepository getBooks();
}
