package com.anmoworkz.nytimes.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anmoworkz.nytimes.view.BookWebViewActivity;
import com.anmoworkz.nytimes.R;
import com.anmoworkz.nytimes.model.Book;
import com.anmoworkz.nytimes.model.BuyLink;
import com.anmoworkz.nytimes.model.Isbn;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HardCoverBookAdapter extends RecyclerView.Adapter<HardCoverBookAdapter.HardCoverViewHolder>{

    private String TAG=getClass().getSimpleName();
    private Context context;
    LayoutInflater layoutInflater;
    ArrayList<Book> bookArrayList;
   public HardCoverBookAdapter(Context context, ArrayList<Book> bookArrayList){
       this.context=context;
       this.bookArrayList=bookArrayList;

    }
    @NonNull
    @Override
    public HardCoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ny_row, parent, false);

        return new HardCoverViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final HardCoverViewHolder hardCoverViewHolder, final int position) {

       // hardCoverViewHolder.txtTitle.setText(bookArrayList.get(position).getTitle());
        //picasso image load
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(bookArrayList.get(position).getBookImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(hardCoverViewHolder.coverImage);

        hardCoverViewHolder.title.setText("Title: " +bookArrayList.get(position).getTitle());
        hardCoverViewHolder.bookrank.setText("Rank: "+(position+1));
        hardCoverViewHolder.auther.setText("Auther: "+bookArrayList.get(position).getAuthor());
        hardCoverViewHolder.publisher.setText("Publisher: "+bookArrayList.get(position).getPublisher());
        hardCoverViewHolder.description.setText("Description: "+bookArrayList.get(position).getDescription());

        List<Isbn> isbnlist= bookArrayList.get(position).getIsbns();
        final List<BuyLink> buyLinkslist= bookArrayList.get(position).getBuyLinks();

        try{

            hardCoverViewHolder.amazon_isbn.setText(isbnlist.get(0).getIsbn10().toString());
            hardCoverViewHolder.barnes_isbn.setText(isbnlist.get(1).getIsbn10().toString());
            hardCoverViewHolder.indebound_isbn.setText(isbnlist.get(2).getIsbn10().toString());
            for (int i = 0; i <buyLinkslist.size() ; i++) {
                if (buyLinkslist.get(i).getName().toString().equalsIgnoreCase("Local Booksellers")){
                    hardCoverViewHolder.indebound_book_download.setContentDescription(buyLinkslist.get(position).getUrl().toString());
                }
                if (buyLinkslist.get(i).getName().toString().equalsIgnoreCase("Barnes and Noble")){
                    hardCoverViewHolder.barnes_book_download.setContentDescription(buyLinkslist.get(position).getUrl().toString());
                }
            }

        }catch (IndexOutOfBoundsException e){

        }

        hardCoverViewHolder.amazon_book_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent=new Intent(context,BookWebViewActivity.class);
                    intent.putExtra("url",bookArrayList.get(position).getAmazonProductUrl().toString());
                    context.startActivity(intent);

                }catch (IndexOutOfBoundsException e){

                }

            }
        });

        hardCoverViewHolder.barnes_book_download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,BookWebViewActivity.class);
                intent.putExtra("url",hardCoverViewHolder.barnes_book_download.getContentDescription().toString());
                context.startActivity(intent);

            }
        });
        hardCoverViewHolder.indebound_book_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,BookWebViewActivity.class);
                intent.putExtra("url",hardCoverViewHolder.indebound_book_download.getContentDescription().toString());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class HardCoverViewHolder extends RecyclerView.ViewHolder{

        //Textview bind using retrofit
        @BindView(R.id.t_title)TextView title;
        @BindView(R.id.bookrank)TextView bookrank;
        @BindView(R.id.t_auther)TextView auther;
        @BindView(R.id.t_publisher)TextView publisher;
        @BindView(R.id.t_description)TextView description;
        @BindView(R.id.t_amazon_isbn)TextView amazon_isbn;
        @BindView(R.id.t_barnes_isbn)TextView barnes_isbn;
        @BindView(R.id.t_indebound_isbn)TextView indebound_isbn;
        //imageview bind using retrofit
        @BindView(R.id.amazon_book_download)ImageView amazon_book_download;
        @BindView(R.id.barnes_book_download)ImageView barnes_book_download;
        @BindView(R.id.indebound_book_download)ImageView indebound_book_download;
        @BindView(R.id.i_image)ImageView coverImage;

        public HardCoverViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }


}
