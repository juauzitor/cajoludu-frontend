package br.cajoludu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.cajoludu.model.Book;

public class BookViewActivity extends AppCompatActivity {
    private Button downloadButton;
    private Context context;
    private String downloadUrl;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        String tituloLivro = getIntent().getStringExtra("livroTitulo");
        String sinopseLivro = getIntent().getStringExtra("livroSinopse");
        String urlLivro = getIntent().getStringExtra("livrourl");
        ImageView imageView = findViewById(R.id.capa);
        imageView.setImageResource(R.drawable.livro);

        fileName = tituloLivro + ".pdf";
        downloadUrl = urlLivro;

        TextView titulo = findViewById(R.id.textView7);
        titulo.setText(tituloLivro);

        TextView sinopse = findViewById(R.id.textView9);
        sinopse.setText(sinopseLivro);

        context = BookViewActivity.this;

        downloadButton = findViewById(R.id.buttonDownload);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDownloader fileDownloader = new FileDownloader(context, downloadUrl, fileName);
                fileDownloader.startDownload();
            }
        });
    }
}