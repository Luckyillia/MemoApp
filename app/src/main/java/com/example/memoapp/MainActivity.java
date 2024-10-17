package com.example.memoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private List<ImageButton> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button butto = findViewById(R.id.button);
        buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.karta1));
        buttons.add(findViewById(R.id.karta2));
        buttons.add(findViewById(R.id.karta3));
        buttons.add(findViewById(R.id.karta4));
        buttons.add(findViewById(R.id.karta5));
        buttons.add(findViewById(R.id.karta6));
        buttons.add(findViewById(R.id.karta7));
        buttons.add(findViewById(R.id.karta8));
        buttons.add(findViewById(R.id.karta9));
        buttons.add(findViewById(R.id.karta10));
        buttons.add(findViewById(R.id.karta11));
        buttons.add(findViewById(R.id.karta12));
        buttons.add(findViewById(R.id.karta13));
        buttons.add(findViewById(R.id.karta14));
        buttons.add(findViewById(R.id.karta15));
        buttons.add(findViewById(R.id.karta16));

        for (ImageButton button : buttons) {
            button.setOnClickListener(v -> Log.i("button", "Clicked: " + button));
            button.setEnabled(false);
        }
    }
}

