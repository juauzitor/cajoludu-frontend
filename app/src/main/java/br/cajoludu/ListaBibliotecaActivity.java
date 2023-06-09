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
    private Book mlivro;
    DatabaseReference livros = reference.child("livros");
    private String id = "001";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_biblioteca);

        tableLayout = findViewById(R.id.tableLayout); // Assuming you have a TableLayout with id "tableLayout"

        livros.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Recupera os dados do snapshot
                    String nome = snapshot.child("nome").getValue(String.class);
                    int curtidas = snapshot.child("curtidas").getValue(int.class);
                    String sinopse = snapshot.child("sinopse").getValue(String.class);
                    String url = snapshot.child("url").getValue(String.class);

                    // Cria o objeto Book
                    mlivro = new Book(nome, curtidas, sinopse, url);

                    // Chama o método para criar a tabela
                    criarTabela();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Trata o erro, se necessário
            }
        });
    }

    private void criarTabela() {
        // Create a new TableRow
        TableRow tableRow = new TableRow(this);

        // Create an ImageButton
        ImageButton imageButton = new ImageButton(this);
        // Set image resource for the ImageButton
        imageButton.setImageResource(R.drawable.livro);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirBookView();
            }
        });

        // Create a TextView
        TextView textView = new TextView(this);
        // Set text for the TextView
        textView.setText(mlivro.getNome());

        // Create layout parameters for the TableRow
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // Width
                ViewGroup.LayoutParams.WRAP_CONTENT // Height
        );

        // Set layout parameters for the TableRow
        tableRow.setLayoutParams(rowParams);

        // Create layout parameters for the ImageButton
        TableRow.LayoutParams imageParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // Width
                ViewGroup.LayoutParams.WRAP_CONTENT // Height
        );

        // Create layout parameters for the TextView
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // Width
                ViewGroup.LayoutParams.WRAP_CONTENT // Height
        );

        // Add the ImageButton and TextView to the TableRow with their respective layout parameters
        tableRow.addView(imageButton, imageParams);
        tableRow.addView(textView, textParams);

        // Add the TableRow to the TableLayout
        tableLayout.addView(tableRow, rowParams);
    }

    public void abrirBookView() {
        Intent intent = new Intent(this, BookViewActivity.class);
        intent.putExtra("livroTitulo", mlivro.getNome());
        intent.putExtra("livroSinopse", mlivro.getSinopse());
        intent.putExtra("livrourl", mlivro.getUrl());
        startActivity(intent);
    }
}