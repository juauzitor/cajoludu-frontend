package br.cajoludu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.cajoludu.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    //creating variables for arraylist and context.
    private ArrayList<Book> bookInfoArrayList;
    private Context mcontext;

    //creating constructor for array list and context.
    public BookAdapter(ArrayList<Book> bookInfoArrayList, Context mcontext) {
        this.bookInfoArrayList = bookInfoArrayList;
        this.mcontext = mcontext;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating our layout for item of recycler view item.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        //inside on bind view holder method we are setting ou data to each UI component.
        Book bookInfo = bookInfoArrayList.get(position);
        holder.nameTV.setText(bookInfo.getTitulo());
        holder.publisherTV.setText(bookInfo.getPublicadora());
        holder.pageCountTV.setText("No of Pages : " + bookInfo.getContagemDePagina());
        holder.dateTV.setText(bookInfo.getSinopse());
        //below line is use to set image from URL in our image view.
        Picasso.get().load(bookInfo.getImagem()).into(holder.bookIV);

        //below line is use to add on click listner for our item of recycler view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inside on click listner method we are calling a new activity and passing all the data of that item in next intent.
                Intent i = new Intent(mcontext, BookViewActivity.class);
                i.putExtra("title", bookInfo.getTitulo());
                i.putExtra("subtitle", bookInfo.getSubtitulo());
                i.putExtra("authors", bookInfo.getAutores());
                i.putExtra("publisher", bookInfo.getPublicadora());
                i.putExtra("publishedDate", bookInfo.getDataPublicacao());
                i.putExtra("description", bookInfo.getSinopse());
                i.putExtra("pageCount", bookInfo.getContagemDePagina());
                i.putExtra("thumbnail", bookInfo.getImagem());
                i.putExtra("previewLink", bookInfo.getPreviewLink());
                i.putExtra("infoLink", bookInfo.getInfoLink());
                i.putExtra("buyLink", bookInfo.getBuyLink());
                //after passing that data we are starting our new  intent.
                mcontext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        //inside get item count method we are returning the size of our array list.
        return bookInfoArrayList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        //below line is use to initialize our text view and image views.
        TextView nameTV, publisherTV, pageCountTV, dateTV;
        ImageView bookIV;

        public BookViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVBookTitle);
            publisherTV = itemView.findViewById(R.id.idTVpublisher);
            pageCountTV = itemView.findViewById(R.id.idTVPageCount);
            dateTV = itemView.findViewById(R.id.idTVDate);
            bookIV = itemView.findViewById(R.id.idIVbook);
        }
    }
}
