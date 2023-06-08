package br.cajoludu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;

import br.cajoludu.model.Book;

public class ListaBibliotecaActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private Book mlivro = new Book("O Alienista", 0,"O alienista é um clássico literário sobre a psiquê. Qual é o limite entre a loucura e a sanidade? Até onde a ciência é capaz de desvendar a mente humana?");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_biblioteca);

        tableLayout = findViewById(R.id.tableLayout); // Assuming you have a TableLayout with id "tableLayout"

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
        startActivity(intent);
    }
}