package com.lcl6.cn.component.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lcl6.cn.component.R;


/**
 * 带进度条的WebView
 * 
 */
@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

	private ProgressBar progressbar;
	public ProgressWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		progressbar =(ProgressBar) LayoutInflater.from(context).inflate(R.layout.progressbar, null);
		//progressbar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbarclor));
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 3, 0, 0));
		addView(progressbar);
		// setWebViewClient(new WebViewClient(){});
		setWebChromeClient(new WebChromeClient());
		setWebViewClient(new MyWebViewClient());
		getSettings().setJavaScriptEnabled(true);
		
	}

	public class WebChromeClient extends android.webkit.WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			if (newProgress == 100) {
				progressbar.setVisibility(GONE);
			} else {
				if (progressbar.getVisibility() == GONE)
					progressbar.setVisibility(VISIBLE);
				progressbar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		progressbar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}

	class MyWebViewClient extends WebViewClient {

		@Override

		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			// 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
			view.loadUrl(url);
			return true;

		}

	}
}