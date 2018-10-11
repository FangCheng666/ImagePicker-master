package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author fc
 * created  2018/10/9 17:46.
 */

public class ImageThumbAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<ImageItem> mImageItems;
    private ImagePicker imagePicker;
    private Map<Integer,ViewHolder> viewHolderMap = new HashMap<>();
    private int currentSelected;

    public ImageThumbAdapter(Activity mActivity, List<ImageItem> mImageItems, ImagePicker imagePicker) {
        this.mActivity = mActivity;
        this.mImageItems = mImageItems;
        this.imagePicker = imagePicker;
    }

    @Override
    public int getCount() {
        return mImageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_htv_img, parent, false);

        holder = new ViewHolder();
        holder.v_border = view.findViewById(R.id.v_border);
        holder.iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);

        viewHolderMap.put(position,holder);
        ImageItem imageItem = mImageItems.get(position);
        imagePicker.getImageLoader().displayImage(mActivity, imageItem.path, holder.iv_thumb, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT); //显示图片
        setThumbSelected(currentSelected);

        return view;
    }

    public void setThumbSelected(int position){

        currentSelected = position;

        for(Map.Entry<Integer,ViewHolder> entry  : viewHolderMap.entrySet()){

            entry.getValue().v_border.setSelected(false);
        }

        viewHolderMap.get(position).v_border.setSelected(true);
    }

    class ViewHolder {
        View v_border;
        ImageView iv_thumb;
    }
}
