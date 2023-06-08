package br.cajoludu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.cajoludu.model.Book;

public class BookViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        String tituloLivro = getIntent().getStringExtra("livroTitulo");
        String sinopseLivro = getIntent().getStringExtra("livroSinopse");
        ImageView imageView = findViewById(R.id.capa);
        imageView.setImageResource(R.drawable.livro);

        TextView titulo = findViewById(R.id.textView7);
        titulo.setText(tituloLivro);

        TextView sinopse = findViewById(R.id.textView9);
        sinopse.setText(sinopseLivro);
    }
}