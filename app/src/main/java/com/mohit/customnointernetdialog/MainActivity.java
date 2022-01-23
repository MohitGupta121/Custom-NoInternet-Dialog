package com.mohit.customnointernetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv);

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info==null || !info.isConnected() || !info.isAvailable()){
            showNoInternetDialog();
            textView.setText("NO INTERNET");
        }else{
            textView.setText("Internet Available");
        }
    }

    private void showNoInternetDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.nointernet_alert_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations =
                android.R.style.Animation_Dialog;

        Button button = dialog.findViewById(R.id.bt_try_again);
        button.setOnClickListener(v -> recreate());
        dialog.show();
    }
}