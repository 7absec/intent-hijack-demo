package com.tabsec.intenthijack2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 500, 50, 100);

        TextView header = new TextView(this);
        header.setText("Intent Hijack Demo 2");
        header.setTextSize(24);
        layout.addView(header);

        TextView result = new TextView(this);
        result.setTextSize(18);
        layout.addView(result);

        Intent intent = getIntent();
        String action = intent.getAction();
        String extraData = intent.getStringExtra("secret");

        StringBuilder displayText = new StringBuilder();
        displayText.append("Intent Action: ").append(action).append("\n");

        if (extraData != null) {
            displayText.append("Hijacked Data: ").append(extraData);
        } else {
            displayText.append("No 'secret' extra found.");
        }

        result.setText(displayText.toString());

        setContentView(layout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
    }
}