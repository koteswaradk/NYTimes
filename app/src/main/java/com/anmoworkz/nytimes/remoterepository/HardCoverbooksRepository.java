package com.anmoworkz.nytimes.remoterepository;

import android.arch.lifecycle.MutableLiveData;

import com.anmoworkz.nytimes.network.APIInterface;
import com.anmoworkz.nytimes.network.RetrofitClientInstance;
import com.anmoworkz.nytimes.model.Book;
import com.anmoworkz.nytimes.model.HardCover;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HardCoverbooksRepository {


    @Inject
   public HardCoverbooksRepository(){

    }
    public MutableLiveData<HardCover> getBooks(){

            final MutableLiveData<HardCover> hardcoverbookPojoMutableLiveData = new MutableLiveData<>();
        APIInterface booksservice= RetrofitClientInstance.getClient().create(APIInterface.class);
        Call<HardCover> hardCoverCall=booksservice.getBookDetails();
        hardCoverCall.enqueue(new Callback<HardCover>() {
            @Override
            public void onResponse(Call<HardCover> call, Response<HardCover> response) {

               // ArrayList<Book> bookArrayList= new ArrayList(response.body().getResults().getBooks());
                hardcoverbookPojoMutableLiveData.setValue(response.body());


            }

            @Override
            public void onFailure(Call<HardCover> call, Throwable t) {

            }
        });
        return hardcoverbookPojoMutableLiveData;
    }


}
