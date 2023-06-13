package br.cajoludu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.cajoludu.model.Book;

public class ListaBibliotecaActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ArrayList<Book> bookInfoArrayList;
    private ProgressBar progressBar;
    private EditText searchEdt;
    private ImageButton searchBtn;
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_biblioteca);

        progressBar = findViewById(R.id.idLoadingPB);
        searchEdt = findViewById(R.id.idEdtSearchBooks);
        searchBtn = findViewById(R.id.idBtnSearch);

        currentUser = getIntent().getStringExtra("user");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (searchEdt.getText().toString().isEmpty()) {
                    searchEdt.setError("Please enter a search query");
                    return;
                }
                getBooksInfo(searchEdt.getText().toString());
            }
        });
    }

    private void getBooksInfo(String query) {
        bookInfoArrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(ListaBibliotecaActivity.this);
        mRequestQueue.getCache().clear();
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        RequestQueue queue = Volley.newRequestQueue(ListaBibliotecaActivity.this);

        JsonObjectRequest booksObjrequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray itemsArray = response.getJSONArray("items");

                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject itemsObj = itemsArray.getJSONObject(i);
                        JSONObject volumeObj = itemsObj.getJSONObject("volumeInfo");
                        String title = volumeObj.optString("title");
                        String subtitle = volumeObj.optString("subtitle");
                        JSONArray authorsArray = volumeObj.getJSONArray("authors");
                        String publisher = volumeObj.optString("publisher");
                        String publishedDate = volumeObj.optString("publishedDate");
                        String description = volumeObj.optString("description");
                        int pageCount = volumeObj.optInt("pageCount");
                        JSONObject imageLinks = volumeObj.optJSONObject("imageLinks");
                        String thumbnail = imageLinks.optString("thumbnail");
                        String previewLink = volumeObj.optString("previewLink");
                        String infoLink = volumeObj.optString("infoLink");
                        JSONObject saleInfoObj = itemsObj.optJSONObject("saleInfo");
                        String buyLink = saleInfoObj.optString("buyLink");
                        ArrayList<String> authorsArrayList = new ArrayList<>();
                        if (authorsArray.length() != 0) {
                            for (int j = 0; j < authorsArray.length(); j++) {
                                authorsArrayList.add(authorsArray.optString(i));
                            }
                        }
                        Book bookInfo = new Book(title, subtitle, authorsArrayList, publisher, publishedDate, description, pageCount, thumbnail, previewLink, infoLink, buyLink);
                        bookInfoArrayList.add(bookInfo);
                    }

                    BookAdapter adapter = new BookAdapter(bookInfoArrayList, ListaBibliotecaActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListaBibliotecaActivity.this, RecyclerView.VERTICAL, false);
                    RecyclerView mRecyclerView = findViewById(R.id.idRVBooks);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ListaBibliotecaActivity.this, "No Data Found" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListaBibliotecaActivity.this, "Error found: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(booksObjrequest);
    }
}
