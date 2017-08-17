package com.lcl6.cn.basedialog.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liancl on 2017/8/17.
 */

public class JsoupAdapter extends BaseRecyclerViewAdapter<JsoupBean> {
    public JsoupAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, int position) {
        JsoupBean item = getItem(position);
        initItem((JsoupViewHolder)holder,item);
    }

    private void initItem(final JsoupViewHolder holder, JsoupBean item) {
        holder.title.setText(item.getTitle());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
        Glide.with(getContext()).load("http:"+item.getAvatar()).apply(options).into(holder.avatar);
//        Glide.with(getContext()).load(item.getAvatar()).into(holder.avatar);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JsoupViewHolder(getLayoutView(parent, R.layout.item_jsoup)) ;
    }
    public class JsoupViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_tilte)
        TextView title;
        @BindView(R.id.img_avatar)
        ImageView avatar;
        public JsoupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
