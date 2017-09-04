package com.lcl6.cn.basedialog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liancl on 2017/8/31.
 */

public class EdtextAdapter extends BaseRecyclerViewAdapter<String> {

    public EdtextAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, final int position) {

        ((EditextHolder)holder).input.setText(position+"");
        ((EditextHolder)holder).textViewposition.setText("测试点击事件"+position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditextHolder(getLayoutView(parent, R.layout.item_editext));
    }
    class EditextHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ed_intput)
        TextView input;
        @BindView(R.id.tv_pos)
        TextView textViewposition;
        @BindView(R.id.lin_item)
        LinearLayout linitem;

        public EditextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
