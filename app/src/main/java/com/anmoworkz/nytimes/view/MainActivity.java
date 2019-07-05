package com.anmoworkz.nytimes.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.anmoworkz.nytimes.R;
import com.anmoworkz.nytimes.adapter.HardCoverBookAdapter;
import com.anmoworkz.nytimes.model.Book;
import com.anmoworkz.nytimes.model.HardCover;
import com.anmoworkz.nytimes.remoterepository.HardCoverbooksRepository;
import com.anmoworkz.nytimes.viewmodel.HardCoverViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    @Inject
    HardCoverbooksRepository hardCoverbooksRepository;
    @BindView(R.id.recycleview)RecyclerView recyclerView;
    HardCoverBookAdapter adapter;
    ProgressDialog progressDoalog;
    public HardCoverViewModel bViewModel;
    public ArrayList<Book>booksList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        progressDialog();

        bViewModel = ViewModelProviders.of(this).get(HardCoverViewModel.class);

       /* bViewModel.booksrepository.getBooks().observe(this, new Observer<HardCover>() {
            @Override
            public void onChanged(@Nullable HardCover hardCover) {
                booksList.addAll(hardCover.getResults().getBooks());
                if (booksList!=null){
                    generateDataList(booksList);
                    progressDoalog.cancel();
                }else{
                    progressDoalog.cancel();
                }
            }
        });*/

        bViewModel.getBooks().observe(this, new Observer<HardCover>() {
            @Override
            public void onChanged(@Nullable HardCover hardCover) {
                booksList.addAll(hardCover.getResults().getBooks());
                if (booksList!=null){
                    generateDataList(booksList);
                    progressDoalog.cancel();
                }else{
                    progressDoalog.cancel();
                }

               // adapter.notifyDataSetChanged();
            }
        });
        // progressDialog();
       /* APIInterface booksservice= RetrofitClientInstance.getClient().create(APIInterface.class);
        Call<HardCover> hardCoverCall=booksservice.getBookDetails();

        hardCoverCall.enqueue(new Callback<HardCover>() {
            @Override
            public void onResponse(Call<HardCover> call, Response<HardCover> response) {
              *//*  Log.i(TAG,"fffffff"+response.toString());
                Log.i(TAG,"111 "+response.body().getResults().getListName());
                Log.i(TAG,"111 "+response.body().getCopyright());
                Log.i(TAG,"111 "+response.body().getNumResults());
                Log.i(TAG,"111 "+response.body().getLastModified());
                Log.i(TAG,"111 "+response.body().getResults().getListName());
                Log.i(TAG,"111 "+response.body().getResults().getListNameEncoded());
                Log.i(TAG,"111 "+response.body().getResults().getBestsellersDate());
                Log.i(TAG,"111 "+response.body().getResults().getPublishedDate());
                Log.i(TAG,"111 "+response.body().getResults().getPublishedDateDescription());
                Log.i(TAG,"111 "+response.body().getResults().getNextPublishedDate());
                Log.i(TAG,"111 "+response.body().getResults().getPreviousPublishedDate());
                Log.i(TAG,"111 "+response.body().getResults().getDisplayName());
                Log.i(TAG,"111 "+response.body().getResults().getNormalListEndsAt());
                Log.i(TAG,"111 "+response.body().getResults().getUpdated());*//*

              //  ArrayList<HardCover> p= new ArrayList(response.body().getResults().getBooks());
                ArrayList<Book> bookArrayList= new ArrayList(response.body().getResults().getBooks());
                generateDataList(bookArrayList);
                progressDoalog.cancel();

            }

            @Override
            public void onFailure(Call<HardCover> call, Throwable t) {
                progressDoalog.cancel();
            }
        });


    }

   */
    }
    private void progressDialog(){
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }
    private void generateDataList(ArrayList<Book> photoList) {


        adapter=new HardCoverBookAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        //  GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}