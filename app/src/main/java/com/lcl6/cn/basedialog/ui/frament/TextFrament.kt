package com.lcl6.cn.basedialog.ui.frament

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.component.base.frament.LazyFragment
import io.reactivex.subjects.PublishSubject

/**
 * Created by liancl on 2017/9/6.
 */

class TextFrament : LazyFragment() {

    @BindView(R.id.tv_send)
    internal var mSend: TextView? = null

    override fun getAbsLayoutId(): Int {
        return R.layout.frament_text
    }

    override fun initView(view: View, savedInstanceState: Bundle) {
        //        mPublishSubject.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Consumer<String>() {
        //                    @Override
        //                    public void accept(@NonNull String s) throws Exception {
        //
        //                    }
        //                });
    }

    companion object {

        fun newInstance(publishSubject: PublishSubject<String>): TextFrament {
            val args = Bundle()
            val fragment = TextFrament()
            //        args.putParcelable("",publishSubject);
            //        args.putSerializable("publishsubject",publishSubject);
            fragment.arguments = args
            return fragment
        }
    }
}
