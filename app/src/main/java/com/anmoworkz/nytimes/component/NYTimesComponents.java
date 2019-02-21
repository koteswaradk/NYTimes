package com.anmoworkz.nytimes.component;

import com.anmoworkz.nytimes.model.Book;
import com.anmoworkz.nytimes.model.BuyLink;
import com.anmoworkz.nytimes.model.HardCover;
import com.anmoworkz.nytimes.model.Isbn;
import com.anmoworkz.nytimes.model.Results;
import com.anmoworkz.nytimes.remoterepository.HardCoverbooksRepository;
import com.anmoworkz.nytimes.view.MainActivity;

import dagger.Component;

public interface NYTimesComponents {
    HardCoverbooksRepository getBooks();
}
