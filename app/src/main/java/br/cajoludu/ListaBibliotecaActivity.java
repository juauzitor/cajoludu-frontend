package br.cajoludu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import br.cajoludu.model.Book;

public class ListaBibliotecaActivity extends AppCompatActivity {
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private TableLayout tableLayout;
    DatabaseReference livros = reference.child("livros");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_biblioteca);

        tableLayout = findViewById(R.id.tableLayout); // Assuming you have a TableLayout with id "tableLayout"

        livros.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot livroSnapshot : snapshot.getChildren()) {
                        // Recupera os dados do snapshot do livro
                        String nome = livroSnapshot.child("nome").getValue(String.class);
                        int curtidas = livroSnapshot.child("curtidas").getValue(int.class);
                        String sinopse = livroSnapshot.child("sinopse").getValue(String.class);
                        String url = livroSnapshot.child("url").getValue(String.class);

                        // Cria um novo objeto Book para cada livro
                        Book livro = new Book(nome, curtidas, sinopse, url);

                        // Cria uma nova TableRow
                        TableRow tableRow = new TableRow(ListaBibliotecaActivity.this);

                        // Cria um novo ImageButton
                        ImageButton imageButton = new ImageButton(ListaBibliotecaActivity.this);
                        // Define o recurso de imagem para o ImageButton
                        imageButton.setImageResource(R.drawable.livro);
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                abrirBookView(livro);
                            }
                        });

                        // Cria um novo TextView
                        TextView textView = new TextView(ListaBibliotecaActivity.this);
                        // Define o texto para o TextView
                        textView.setText(livro.getNome());

                        // Define os parâmetros de layout para a TableRow
                        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, // Width
                                ViewGroup.LayoutParams.WRAP_CONTENT // Height
                        );

                        // Define os parâmetros de layout para o ImageButton
                        TableRow.LayoutParams imageParams = new TableRow.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT, // Width
                                ViewGroup.LayoutParams.WRAP_CONTENT // Height
                        );

                        // Define os parâmetros de layout para o TextView
                        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT, // Width
                                ViewGroup.LayoutParams.WRAP_CONTENT // Height
                        );

                        // Adiciona o ImageButton e o TextView à TableRow com seus respectivos parâmetros de layout
                        tableRow.addView(imageButton, imageParams);
                        tableRow.addView(textView, textParams);

                        // Adiciona a TableRow ao TableLayout
                        tableLayout.addView(tableRow, rowParams);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Trata o erro, se necessário
            }
        });
    }

    public void abrirBookView(Book livro) {
        Intent intent = new Intent(this, BookViewActivity.class);
        intent.putExtra("livroTitulo", livro.getNome());
        intent.putExtra("livroSinopse", livro.getSinopse());
        intent.putExtra("livrourl", livro.getUrl());
        startActivity(intent);
    }
}