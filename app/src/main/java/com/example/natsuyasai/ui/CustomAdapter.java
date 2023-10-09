package com.example.natsuyasai.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.natsuyasai.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomListData> {
    private LayoutInflater layoutInflater_;

    public CustomAdapter(Context context, int textViewResourceId, List<CustomListData> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        CustomListData item = (CustomListData)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.list_item, null);
        }


        TextView messageView;
        messageView = (TextView)convertView.findViewById(R.id.message_content);
        messageView.setText(item.getMessageData());

        TextView proView;
        proView = (TextView)convertView.findViewById(R.id.user_pro);
        proView.setText(item.getProfessionData());

        return convertView;
    }
}