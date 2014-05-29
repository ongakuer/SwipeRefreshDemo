package me.relex.swiperefresh;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DemoAdapter extends BaseAdapter {

    private List<String> mList = new ArrayList<String>();

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        mList.clear();
    }

    public void addList(List<String> list) {
        mList.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    android.R.layout.simple_list_item_1, null);
            ViewHolder holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mTextView.setText(mList.get(position));

        return convertView;
    }

    public class ViewHolder {

        private TextView mTextView;

    }

}
