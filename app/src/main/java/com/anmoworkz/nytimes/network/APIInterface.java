package com.anmoworkz.nytimes.network;

import com.anmoworkz.nytimes.model.HardCover;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/svc/books/v3/lists/current/hardcover-fiction.json?api-key=uj85TmuRvmTXkz5HCjby6h8uQHR0vMG4")
    Call<HardCover> getBookDetails();
}
