package com.lcl6.cn.basedialog.util;

import android.view.View;
import android.widget.Toast;

import com.lcl6.cn.basedialog.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liancl on 2017/12/18.
 */

public class HookListener implements View.OnClickListener {
    private View.OnClickListener originalClick;

    public HookListener(View.OnClickListener originalClick) {
        this.originalClick = originalClick;
    }

    @Override
    public void onClick(View v) {

        if(originalClick!=null){
            originalClick.onClick(v);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("hook succeed./n");
        Object obj = v.getTag(R.id.id_hook);
        if (obj != null && obj instanceof HashMap && !((Map) obj).isEmpty()) {
            for (Map.Entry<String, String> entry : ((Map<String, String>) obj).entrySet()) {
                sb.append("key => ")
                        .append(entry.getKey())
                        .append(" ")
                        .append("value => ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }else {
            sb.append("params => null\n");
        }
        Toast.makeText(v.getContext(), sb.toString(), Toast.LENGTH_LONG).show();

    }
}
