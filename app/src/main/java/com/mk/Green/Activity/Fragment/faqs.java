package com.mk.Green.Activity.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mk.Green.Activity.LoginActivity;
import com.mk.Green.Activity.Singleton;
import com.mk.Green.Faq.Faq;
import com.mk.Green.Faq.FaqsAdapter;
import com.mk.Green.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class faqs extends Fragment {

    private static final String URL_PRODUCTS = "http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/activity.php";
    private FaqsAdapter faqsAdapter;
    private ArrayList<Faq> faqs;
    private ExpandableListView expandableListView;
    public faqs() {
    }
    String details, name, disability,caretype,status,price,date,email,parent,planeid;




    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.faq_activity, container, false);
        Bundle b = this.getArguments();
        if(b != null){
            planeid =b.getString("planeid");


        }
        expandableListView = v.findViewById(R.id.expandableListView);
        faqs = getFaqs();
        faqsAdapter = new FaqsAdapter(faqs);
        expandableListView.setAdapter(faqsAdapter);
        final int colorPrimary = ContextCompat.getColor(getActivity(), R.color.colorPrimary);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            View previousGroupView = null;
            TextView currentHeaderTv, prevHeaderTv;

            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View currentView, int groupPosition, long l) {
                currentHeaderTv = currentView.findViewById(R.id.headerTv);
                if (currentView == previousGroupView) {
                    if (expandableListView.isGroupExpanded(groupPosition)) {
                        currentView.setBackgroundColor(Color.WHITE);
                        currentHeaderTv.setTextColor(colorPrimary);
                    } else {
                        currentView.setBackgroundColor(colorPrimary);
                        currentHeaderTv.setTextColor(Color.WHITE);
                    }
                } else {
                    currentView.setBackgroundColor(colorPrimary);
                    currentHeaderTv.setTextColor(Color.WHITE);
                    if (previousGroupView != null) {
                        previousGroupView.setBackgroundColor(Color.WHITE);
                        prevHeaderTv = previousGroupView.findViewById(R.id.headerTv);
                        prevHeaderTv.setTextColor(colorPrimary);
                    }
                }
                previousGroupView = currentView;
                return false;
            }
        });
        return v;
    }
    private ArrayList<Faq> getFaqs() {
        ArrayList<Faq> faqs = new ArrayList<>();

        faqs.add(new Faq("How do I make an Cleaning service Order?",
                "Upload the image of the item to be washed, Include its relevant details.\n" +

                        "after you place an order, we will immediately check the conditions, the availability (and prices if there are changes in the price), " +
                        "And the finance will quote the price of the service in which the customer now will update the payments / " +
                        "transfer money before there is confirmation from us via phone / SMS or email.\n" +
                        "Upon confirmation from us, please send / transfer payment to one of the following bank account\n" +
                        "We only accept cash payments by wire transfer to a bank account."));

        faqs.add(new Faq("Payments?",
                "Make orders with your phone number using the mpesa gateway" +
                        "Green carpet will send information on the number of total expenditures " +
                        "and postage to your email address, for details please check your email!"));

        faqs.add(new Faq("Shipping?",
                "Service prices include shipping costs\n" +
                        "on the weight and volume of goods, destination address and courier used."));

        faqs.add(new Faq("Does our customer care respond?",
                "Yes, fast as well."));

        faqs.add(new Faq("Contact us?",
                "Do you still feel confused by the system that you need? Do not worry, \n" +
                        "please contact us now! Gladly we will help resolve your needs."));

        return faqs;
    }

    private void updateActivity(final String planeid,final String date) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/eda/admin/recyclerview/updateflight.php", new Response.Listener<String>() {
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

                param.put("status1", "pilot");
                param.put("status","Completed");
                param.put("planeid",planeid);
                param.put("date",date);
               // param.put("activity",activity);

                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }

    private void compActivity(final String code) {
        //if (phone.length() == 10) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,"http://"+ LoginActivity.ip+"/bdcms/admin/recyclerview/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Records updated successfully")) {

                    Toast.makeText(getActivity(), "task completed successifully", Toast.LENGTH_SHORT).show();
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

                param.put("status", "complete");
                param.put("code",code);


                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Singleton.getmInstance(getActivity()).addToRequestQueue(request);

    }
}