package com.lcl6.cn.basedialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liancl on 2017/11/22.
 */

public class VideoAdapter extends BaseRecyclerViewAdapter<VideoInfo> {

    public VideoAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, int position) {
        VideoInfo item = getItem(position);
        showItem((ViewHolder)holder,item);

    }

    private void showItem(ViewHolder holder, VideoInfo item) {

        holder.title.setText(item.getTitle());
        Glide.with(App.getAppComponent().getContext()).load(item.getCover()).into(holder.imageView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getLayoutView(parent, R.layout.item_video));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_photo)
        ImageView imageView;

        @BindView(R.id.tv_title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
