package com.example.memoapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private List<ImageButton> buttons;
    private List<Drawable> images;
    private ImageButton buttonIsCheacked1 = null;
    private ImageButton buttonIsCheacked2 = null;
    private Drawable default_image;
    private int count = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button butto = findViewById(R.id.button);
        textView = findViewById(R.id.counter);
        default_image = ContextCompat.getDrawable(this, R.drawable.karty_00);

        images = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String imageName = "karty_0" + i;
            Drawable image = ContextCompat.getDrawable(this, getResources().getIdentifier(imageName, "drawable", getPackageName()));
            images.add(image);
            images.add(image);
        }

        Collections.shuffle(images);

        buttons = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            String buttonID = "karta" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            ImageButton button = findViewById(resID);
            buttons.add(button);
        }

        for (int i = 0; i < buttons.size(); i++) {
            ImageButton button = buttons.get(i);
            int index = i;
            button.setOnClickListener(v -> {
                Drawable currentImage = images.get(index);
                button.setImageDrawable(currentImage);

                if(buttonIsCheacked1 != null && buttonIsCheacked2 != null){
                    buttonIsCheacked1.setImageDrawable(default_image);
                    buttonIsCheacked2.setImageDrawable(default_image);
                    buttonIsCheacked1.setEnabled(true);
                    buttonIsCheacked2 = null;
                    buttonIsCheacked1 = button;
                    buttonIsCheacked1.setEnabled(false);
                }else {
                    if (buttonIsCheacked1 != null) {
                        buttonIsCheacked2 = button;

                        if (buttonIsCheacked1.getDrawable().getConstantState().equals(buttonIsCheacked2.getDrawable().getConstantState())) {
                            buttonIsCheacked1.setEnabled(false);
                            buttonIsCheacked2.setEnabled(false);
                            Toast.makeText(MainActivity.this, "Zgadles!", Toast.LENGTH_SHORT).show();
                            buttonIsCheacked1 = null;
                            buttonIsCheacked2 = null;
                        }
                        count++;
                        textView.setText("Licznik ruchow: "+count);
                    } else {
                        buttonIsCheacked1 = button;
                        buttonIsCheacked1.setEnabled(false);
                    }
                }
            });
        }

        butto.setOnClickListener(v -> {
            Collections.shuffle(images);
            for (ImageButton b : buttons) {
                b.setImageDrawable(default_image);
                b.setEnabled(true);
            }
            count = 0;
            textView.setText("Licznik ruchow: "+count);
            buttonIsCheacked1 = null;
            buttonIsCheacked2 = null;
        });
    }
}
