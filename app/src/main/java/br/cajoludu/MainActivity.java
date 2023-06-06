package br.cajoludu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = MainActivity.this; // Replace YourActivity with your actual activity or context
        String downloadUrl = "https://wiki.sj.ifsc.edu.br/images/7/7f/FTP-RES2014-1.pdf";
        String fileName = "FTP-RES2014-1.pdf";

        FileDownloader fileDownloader = new FileDownloader(context, downloadUrl, fileName);
        fileDownloader.startDownload();
    }
}