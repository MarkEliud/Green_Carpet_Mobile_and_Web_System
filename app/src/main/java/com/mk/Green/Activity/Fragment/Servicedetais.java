package com.mk.Green.Activity.Fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.R;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class Servicedetais extends Fragment {


    RecyclerView recyclerView;

    public Servicedetais() {
    }

    String name, description, image, title, amount, date, email, address, code, status,co;
    private LinearLayout pdfLayout;
    private LinearLayout receiptLayout;
    //View content;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail, container, false);
        Bundle b = this.getArguments();
        if (b != null) {


            title = b.getString("title");
            description = b.getString("description");
            image = b.getString("image");
            name = b.getString("name");
            amount = b.getString("amount");
            date = b.getString("date");
            email = b.getString("email");
            address = b.getString("address");
            code = b.getString("code");
            status = b.getString("status");

        }

        ImageView imagev = v.findViewById(R.id.image_view);
        TextView nameq = v.findViewById(R.id.kid);
        if(status.equals("Not Paid Yet")){
            co="Pending";
        }
        nameq.setText("Customer's Name:" + name + "\n" + "Item's Name: " + title + "\n" + "Description: " + description + "\n" + "M'pesa Code: " + code + "\n"
                + "Amount paid: " + amount + "\n" + "Date: " + date + "\n" + "Status: " + co + "\n");
        Picasso.get().load(image).into(imagev);



        // Generate the PDF
        Button generatePdfButton = v.findViewById(R.id.generate);
       // Button generatePdfButton = findViewById(R.id.generatePdfButton);
        generatePdfButton.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                View content = inflater.inflate(R.layout.receipt, null);
                TextView name1, caretype1,status1, price1, disability1,date1,from;
                name1=content.findViewById(R.id.namenow1);
                name1.setText(name);
                caretype1=content.findViewById(R.id.rrrom);
                from=content.findViewById(R.id.rrprice);
                status1=content.findViewById(R.id.paymentFor);
                price1=content.findViewById(R.id.sum);
                disability1=content.findViewById(R.id.resr);
                date1=content.findViewById(R.id.datenow);
                price1.setText("Kshs:- "+amount+" only.");
                disability1.setText("Service name:- "+title);

             //   Toast.makeText(getActivity(), "failureResponse", Toast.LENGTH_SHORT).show();


                    caretype1.setText("Description:- "+description);

                from.setText("Customer Email:- "+email);
                status1.setText(co);
                date1.setText(date);
                convertXmlToPdf(content);
//                PdfGenerator.getBuilder()
//                        .setContext(getContext())
//                        .fromViewSource()
//                        .fromView(content)
//                        .setFileName("Green Carpet Receipt")
//                        .setFolderName("PDF-folder")
//                        .openPDFafterGeneration(true)
//                        .build(new PdfGeneratorListener() {
//                            @Override
//                            public void onFailure(FailureResponse failureResponse) {
//                                super.onFailure(failureResponse);
//                            }
//                            @Override
//                            public void onStartPDFGeneration() {
//
//                            }
//                            @Override
//                            public void onFinishPDFGeneration() {
//
//                            }
//                            @Override
//                            public void showLog(String log) {
//                                super.showLog(log);
//                            }
//
//                            @Override
//                            public void onSuccess(SuccessResponse response) {
//                                super.onSuccess(response);
//                            }
//                        });

            }
        });



        Button appr = v.findViewById(R.id.appr);
        appr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(status.equals("Completed")){
                    Toast.makeText(getActivity(), "Service Completed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Cleaner has not Finished Service yet", Toast.LENGTH_SHORT).show();

                }
             //   updateStatus(code, "Rejected");
            }
        });
        return v;
    }

    private void updateStatus(final String code, final String status) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, LoginActivity.ip+"recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    // finish();
                } else {
                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();

                param.put("status", status);
                param.put("code", code);

                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
    public void convertXmlToPdf(View content) {
        // Inflate the XML layout file
        // View view = LayoutInflater.from(this).inflate(R.layout.receipt_layout, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getActivity().getDisplay().getRealMetrics(displayMetrics);
        } else {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }

        content.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY));
        Log.d("mylog", "Width Now " + content.getMeasuredWidth());
        content.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);

        // Create a new PdfDocument instance
        PdfDocument document = new PdfDocument();

        // Obtain the width and height of the view
        //int viewWidth = view.getMeasuredWidth();
        int viewHeight = content.getMeasuredHeight();
        int viewWidth = 1080;
        //  int viewHeight = 1920;

        // Create a PageInfo object specifying the page attributes
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(viewWidth, viewHeight, 1).create();

        // Start a new page
        PdfDocument.Page page = document.startPage(pageInfo);

        // Get the Canvas object to draw on the page
        Canvas canvas = page.getCanvas();

        // Create a Paint object for styling the view
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        // Draw the view on the canvas
        content.draw(canvas);

        // Finish the page
        document.finishPage(page);

        // Specify the path and filename of the output PDF file
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "GreenCarpet_Receipt" + timestamp + ".pdf";
        File filePath = new File(downloadsDir, fileName);

        try {
            // Save the document to a file
            FileOutputStream fos = new FileOutputStream(filePath);
            document.writeTo(fos);
            document.close();
            fos.close();
            // PDF conversion successful
            Toast.makeText(getActivity(), "Generating Successful", Toast.LENGTH_LONG).show();

            // Open the PDF file using an intent
            Uri pdfUri = FileProvider.getUriForFile(requireContext(), "com.mk.metropolitan.fileprovider", filePath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Open PDF"));

        } catch (IOException e) {
            e.printStackTrace();
            // Error occurred while converting to PDF
        }
    }

}