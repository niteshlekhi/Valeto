package lekhicomp.com.valeto.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class ButtonAdapter extends BaseAdapter {

    private ArrayList mButtons = null;

    public ButtonAdapter(ArrayList b) {
        mButtons = b;
    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object) mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
//in our case position and id are synonymous
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FButton button;
        if (convertView == null) {
            button = (FButton) mButtons.get(position);
        } else {
            button = (FButton) convertView;
        }
        return button;
    }
}

