package com.example.independentfragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WebviewActivity extends AppCompatActivity implements webViewListener {
    private WebviewFragment myReusableFragment;

    @Override
    protected void onCreate(Bundle savedInstance) {
        setContentView(R.layout.webview_layout);
        myReusableFragment = WebviewFragment.getMyInstance();

        super.onCreate(savedInstance);
    }
    @Override
    protected void onStart() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.webview_activity_layout, myReusableFragment);
        fragmentTransaction.commit();

        super.onStart();
    }
}
