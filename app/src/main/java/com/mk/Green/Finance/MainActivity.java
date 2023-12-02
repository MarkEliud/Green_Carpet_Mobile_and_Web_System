package com.mk.Green.Finance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.mk.Green.Activity.Adapters.Image;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Driver.Fragments.displaymessage;
import com.mk.Green.Finance.Fragments.approvedFrag;
import com.mk.Green.Finance.Fragments.incomingFrag;

import com.mk.Green.Finance.Fragments.supplyApprove;
import com.mk.Green.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        final Intent intent = getIntent();
        email = intent.getStringExtra("email");
        FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
        incomingFrag fragB = new incomingFrag();
        // fragB.setArguments(b);
        transaction.replace(R.id.my_frame_layout,fragB);
        transaction.addToBackStack(null)
                .commit();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction1 = MainActivity.this.getSupportFragmentManager().beginTransaction();
                Bundle b = new Bundle();
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.incoming:
                        //b.putString("email",email);
                        incomingFrag fragB = new incomingFrag();
                       // fragB.setArguments(b);
                      transaction1.replace(R.id.my_frame_layout,fragB);
                        transaction1.addToBackStack(null)
                                .commit();
                          break;
                    case R.id.approved:
                  //      b.putString("email",email);
                        approvedFrag fragA = new approvedFrag();
                       // fragA.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragA);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.approvs:
                        //      b.putString("email",email);
                        supplyApprove fragv = new supplyApprove();
                        // fragA.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragv);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.pdf:
                    loadProgrammes();
                        break;
                    case R.id.mess:
                        b.putString("user","Finance");
                        displaymessage fragb = new displaymessage();
                        fragb.setArguments(b);
                        transaction1.replace(R.id.my_frame_layout,fragb);
                        transaction1.addToBackStack(null)
                                .commit();
                        break;
                    case R.id.logout:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;

                    default:
                        return true;

                }
                return true;
            }
        });


    }

    private String pdfFilePath;

    // Constants for table layout
    private static final int PAGE_WIDTH = 612; // 8.5 inches * 72 dpi
    private static final int PAGE_HEIGHT = 792; // 11 inches * 72 dpi
    private static final int TEXT_SIZE = 12;
    private static final int LEFT_MARGIN = 40;
    private static final int TABLE_TOP_MARGIN = 40;
    private static final int COLUMN_WIDTH = 100;
    private static final int ROW_HEIGHT = 40;
    private void loadProgrammes() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            List<Image> productList = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                productList.add(new Image(

                                        jsonObject.getString("name"),
                                        jsonObject.getString("date"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("imageUrl"),

                                        jsonObject.getString("status"),
                                        jsonObject.getString("customerName"),
                                        jsonObject.getString("code"),
                                        jsonObject.getString("address"),
                                        jsonObject.getString("amount"),
                                        jsonObject.getString("fprice"),
                                        jsonObject.getString("email")
                                ));
                            }
                            //Toast.makeText(MainActivity.this, "llll", Toast.LENGTH_SHORT).show();
                            // Generate the PDF report with the JSON data
                            generatePdfFromJson(productList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void generatePdfFromJson(List<Image> productList) {
        // Create a new PdfDocument
        PdfDocument pdfDocument = new PdfDocument();

        // Create a page info with the page attributes
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, 1).create();

        // Start a new page
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        // Create a Canvas object to draw on the page
        Canvas canvas = page.getCanvas();

        // Set up the paint for drawing text
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(TEXT_SIZE);

        // Load the custom font for table headers
        //   Typeface typeface = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.ttf");
        //  paint.setTypeface(typeface);

        // Calculate the total amount
        double totalAmount = 0;

        // Draw the table headers with custom background color and rounded corners
        RectF headerRect = new RectF(LEFT_MARGIN, TABLE_TOP_MARGIN, LEFT_MARGIN + 6 * COLUMN_WIDTH, TABLE_TOP_MARGIN + ROW_HEIGHT);
        paint.setColor(Color.parseColor("#ECEFF1")); // Light gray color for headers
        canvas.drawRoundRect(headerRect, 16, 16, paint);
        paint.setColor(Color.BLACK);
        canvas.drawText("Customer Name", LEFT_MARGIN + 20, TABLE_TOP_MARGIN + 30, paint);
        canvas.drawText("Service name", LEFT_MARGIN + COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + 30, paint);
        canvas.drawText("Date", LEFT_MARGIN + 2 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + 30, paint);
        canvas.drawText("Amount", LEFT_MARGIN + 3 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + 30, paint);
        canvas.drawText("Code", LEFT_MARGIN + 4 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + 30, paint);
        canvas.drawText("Status", LEFT_MARGIN + 5 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + 30, paint);

        // Draw the table content with alternating row colors
        int tableRow = 1;
        for (Image product : productList) {
            double amount = Double.parseDouble(product.getAmount());
            totalAmount += amount;

            // Draw the row background with alternating colors
            RectF rowRect = new RectF(LEFT_MARGIN, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT,
                    LEFT_MARGIN + 6 * COLUMN_WIDTH, TABLE_TOP_MARGIN + (tableRow + 1) * ROW_HEIGHT);
            paint.setColor(tableRow % 2 == 0 ? Color.WHITE : Color.parseColor("#F5F5F5")); // Alternating row colors
            canvas.drawRoundRect(rowRect, 8, 8, paint);

            // Draw the table content text
            paint.setColor(Color.BLACK);
            canvas.drawText(product.getCustomerName(), LEFT_MARGIN + 20, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT + 30, paint);
            canvas.drawText(product.getName(), LEFT_MARGIN + COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT + 30, paint);
            canvas.drawText(product.getDate(), LEFT_MARGIN + 2 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT + 30, paint);
            canvas.drawText(product.getAmount(), LEFT_MARGIN + 3 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT + 30, paint);
            canvas.drawText(product.getCode(), LEFT_MARGIN + 4 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT + 30, paint);
            canvas.drawText(product.getStatus(), LEFT_MARGIN + 5 * COLUMN_WIDTH + 20, TABLE_TOP_MARGIN + tableRow * ROW_HEIGHT + 30, paint);

            tableRow++;
        }

        // Draw the total amount at the bottom with a separate background color
        RectF totalRect = new RectF(LEFT_MARGIN, TABLE_TOP_MARGIN + (tableRow + 1) * ROW_HEIGHT,
                LEFT_MARGIN + 6 * COLUMN_WIDTH, TABLE_TOP_MARGIN + (tableRow + 2) * ROW_HEIGHT);
        paint.setColor(Color.parseColor("#ECEFF1")); // Light gray color for total row
        canvas.drawRoundRect(totalRect, 8, 8, paint);

        String totalAmountString = "Total Amount: KSHS" + totalAmount;
        paint.setColor(Color.BLACK);
        canvas.drawText(totalAmountString, LEFT_MARGIN + 20, TABLE_TOP_MARGIN + (tableRow + 1) * ROW_HEIGHT + 30, paint);

        // Finish the page
        pdfDocument.finishPage(page);

        // Save the PDF to a file
        String fileName = "report.pdf";
        pdfFilePath = new File(getExternalFilesDir(null), fileName).getAbsolutePath();

       // pdfFilePath = new File(getFilesDir(), fileName).getAbsolutePath();
        try {
            pdfDocument.writeTo(new FileOutputStream(pdfFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the document
        pdfDocument.close();

        // Open the generated PDF after saving
        openGeneratedPdf();
    }


    private void openGeneratedPdf() {
        File pdfFile = new File(pdfFilePath);
        if (pdfFile.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri pdfUri = FileProvider.getUriForFile(this, "com.mk.metropolitan.fileprovider", pdfFile);

           // Uri pdfUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", pdfFile);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);
        } else {
            Log.e("PDF", "Generated PDF file does not exist.");
        }
    }

    private static final String link =LoginActivity.ip+"recyclerview/displayApproved.php";

}