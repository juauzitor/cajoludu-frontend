package br.cajoludu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout); // Assuming you have a TableLayout with id "tableLayout"

        // Create a new TableRow
        TableRow tableRow = new TableRow(this);

        // Create an ImageButton
        ImageButton imageButton = new ImageButton(this);
        // Set image resource for the ImageButton
        imageButton.setImageResource(R.drawable.livro);

        // Create a TextView
        TextView textView = new TextView(this);
        // Set text for the TextView
        textView.setText("Text");

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
}
