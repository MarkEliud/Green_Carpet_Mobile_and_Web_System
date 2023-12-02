package com.mk.Green.Faq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mk.Green.R;

import java.util.ArrayList;



/**
 * Created by Shishir on 13/06/2019.
 */

public class FaqsAdapter extends BaseExpandableListAdapter {

    private ArrayList<Faq> faqs;

    public FaqsAdapter(ArrayList<Faq> faqs) {
        this.faqs = faqs;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return faqs.get(groupPosition).getAnswer();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_answer_item_view, null);
        }
        TextView answerTv = convertView.findViewById(R.id.contentTv);
        String answer = getChild(groupPosition, childPosition);
        answerTv.setText(answer);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1; // As Answer always one...........
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return faqs.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return faqs.size();
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Faq group = (Faq) getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_question_item_view, null);
        }
        TextView tv = convertView.findViewById(R.id.headerTv);
        tv.setText(group.getQuestion());
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}