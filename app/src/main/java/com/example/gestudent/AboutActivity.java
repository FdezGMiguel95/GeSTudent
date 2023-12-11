package com.example.gestudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity {

    TextView textView_PDF;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textView_PDF = findViewById(R.id.textView_PDF);
        textView_PDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPDF(R.raw.avisolegal);
            }
        });
    }

    private void abrirPDF(int rawPDF) {
        try {
            //Abre el inputStream para el recurso RAW
            InputStream inputStream = getResources().openRawResource(rawPDF);

            //Crear un array de bytes para leer el contenido del pdf
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            String pdfFileName = "avisolegal.pdf";//Nombre del archivo PDF

            File tempFile = new File(getFilesDir(), pdfFileName);
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            outputStream.write(buffer);
            outputStream.close();

            fileUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", tempFile);

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(fileUri, "application/pdf");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                startActivity(i);
            }catch (ActivityNotFoundException e){
                Toast.makeText(this, "No hay aplicación para abrir un pdf en su dispositivo", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}