package com.lulee007.xitu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lulee007.xitu.R;
import com.lulee007.xitu.base.XTBaseAdapter;
import com.lulee007.xitu.models.Entry;
import com.lulee007.xitu.util.DateUtil;

/**
 * User: lulee007@live.com
 * Date: 2015-12-13
 * Time: 17:18
 */
public class EntryListItemAdapter extends XTBaseAdapter<Entry> {
    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        RecyclerView.ViewHolder viewHolder = new EntryViewHolder(view);
        return viewHolder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_list_item, parent,false);

        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isItemViewHolder(position)) {
            EntryViewHolder entryViewHolder = (EntryViewHolder) holder;
            Entry entry = getItem(position);
            if (entry != null) {
                entryViewHolder.title.setText(entry.getTitle());
                entryViewHolder.hotIndex.setText(entry.getHotIndex() + "");
                entryViewHolder.createTime.setText(DateUtil.upToNow(entry.getCreatedAt()));
                entryViewHolder.author.setText(entry.getUser().getUsername());

                entryViewHolder.collectionCount.setText(entry.getCollectionCount() + "");
                if (entry.getScreenshot() != null)
                    Glide.with(entryViewHolder.itemView.getContext())
                            .load(entry.getScreenshot().getUrl())
                            .crossFade()
                            .into(entryViewHolder.screenShot);
                else {
                    Glide.with(entryViewHolder.itemView.getContext())
                            .load(R.mipmap.entry_image_default)
                            .crossFade()
                            .into(entryViewHolder.screenShot);
                }

            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    protected class EntryViewHolder extends RecyclerView.ViewHolder {


        public ImageView screenShot;
        public TextView title;
        public TextView author;
        public TextView createTime;
        public TextView collectionCount;
        public TextView hotIndex;

        public EntryViewHolder(View itemView) {
            super(itemView);
            if (itemView != null) {
                screenShot = (ImageView) itemView.findViewById(R.id.entry_screenshot);
                title = (TextView) itemView.findViewById(R.id.entry_title);
                author = (TextView) itemView.findViewById(R.id.entry_author);
                collectionCount = (TextView) itemView.findViewById(R.id.entry_collection_count);
                createTime = (TextView) itemView.findViewById(R.id.entry_create_time);
                hotIndex = (TextView) itemView.findViewById(R.id.entry_hot_index);
            }
        }
    }
}