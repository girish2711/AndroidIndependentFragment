package com.example.independentfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

public class WebviewFragment extends Fragment {
    private WebView webView;
    private webViewListener listener;
    private LinearLayout linearLayout;
    public static WebviewFragment myInstance = null;

    public static WebviewFragment getMyInstance() {
        if (myInstance == null) {
            myInstance = new WebviewFragment();
        }
        return myInstance;
    }

    private WebviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        if (linearLayout.getParent() != null) {
            ((ViewGroup) linearLayout.getParent()).removeView(linearLayout);
        }
        container.addView(linearLayout);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (webViewListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement webViewListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



    public void loadWebview(Activity ctx) {
        linearLayout = new LinearLayout(ctx);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(0xff99ccff);

        webView = new WebView(ctx);
        webView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        webView.setBackgroundColor(0xffffdbdb); // hex color 0xAARRGGBB

        linearLayout.addView(webView);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setAllowFileAccessFromFileURLs(true);
                webView.getSettings().setAllowContentAccess(true);
                webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebContentsDebuggingEnabled(true);
                webView.addJavascriptInterface(new KeyJSInterface(), "KeyJSInterface");
                webView.loadUrl("https://www.key.com/personal/index.jsp");

                webView.setWebViewClient(new KeyWebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {

                        Log.d("DO","Webview load finished");
                    }

                    @Override
                    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                        Log.d("DO","Webview didn't load");
                        Log.d("DO",error.getDescription().toString());

                    }
                });
    }

    private class KeyWebViewClient extends WebViewClient {

        KeyWebViewClient() {

        }
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }
    }

    private class KeyJSInterface {

        @JavascriptInterface
        public void pwdCancel(final String message) {
            Context context = getContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Hello are you good with this toast?", duration);
            toast.show();

        }
    }
}
