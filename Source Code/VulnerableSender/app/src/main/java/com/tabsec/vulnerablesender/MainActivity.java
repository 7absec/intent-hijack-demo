package com.tabsec.vulnerablesender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 500, 50, 100);

        // Header
        TextView header = new TextView(this);
        header.setText("Vulnerable Sender");
        header.setTextSize(24);
        layout.addView(header);

        // Input field
        EditText inputField = new EditText(this);
        inputField.setHint("Enter sensitive data");
        inputField.setTextSize(18);
        layout.addView(inputField);

        // Implicit Intent button
        Button implicitButton = new Button(this);
        implicitButton.setText("1. Implicit Intent Hijacking");
        implicitButton.setTextSize(18);
        layout.addView(implicitButton);

        // Explicit Intent button
        Button explicitButton = new Button(this);
        explicitButton.setText("2. Explicit Intent Hijacking");
        explicitButton.setTextSize(18);
        layout.addView(explicitButton);

        // Implicit intent logic
        implicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sensitiveData = inputField.getText().toString();
                if (sensitiveData.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setType("text/plain");
                intent.putExtra("secret", sensitiveData);

                // Send implicit intent — chooser may appear
                startActivity(intent);
            }
        });

        // Explicit intent logic
        explicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sensitiveData = inputField.getText().toString();
                if (sensitiveData.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.setClassName("com.tabsec.intenthijack", "com.tabsec.intenthijack.MainActivity");
                intent.putExtra("secret", sensitiveData);

                // Send explicit intent — directly to hijack app
                startActivity(intent);
            }
        });

        // Set layout
        setContentView(layout);
    }
}