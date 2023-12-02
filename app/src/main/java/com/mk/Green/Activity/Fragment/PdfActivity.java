package com.mk.Green.Activity.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

//import com.github.barteksc.pdfviewer.PDFView;
import com.mk.Green.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        // Inflate the XML layout view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.faq_question_item_view, null);

        // Convert the XML layout view to a PDF
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(view.getWidth(), view.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        view.draw(page.getCanvas());
        pdfDocument.finishPage(page);

        // Save the PDF to external storage
        File file = new File(getExternalFilesDir(null), "my_pdf_file.pdf");
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();

        // Open the PDF file in a PDF viewer
       // PDFView pdfView = findViewById(R.id.pdfView);
        //pdfView.fromFile(file).load();
    }
}
