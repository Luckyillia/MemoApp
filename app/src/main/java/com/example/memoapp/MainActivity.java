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
    //deklaracja zmienych
    private List<ImageButton> buttons;
    private List<Drawable> images;
    private ImageButton buttonIsCheacked1 = null;
    private ImageButton buttonIsCheacked2 = null;
    private Drawable default_image;
    private int count = 0;
    private TextView textView;
    private boolean finish = false;
    private int[] buttonIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //zmiene dodatkowe
        Button butto = findViewById(R.id.button);
        textView = findViewById(R.id.counter);
        default_image = ContextCompat.getDrawable(this, R.drawable.karty_00);
        Log.w("LogImage", String.valueOf(default_image));

        //tworzenie listy zdjec
        images = new ArrayList<>();
        //tworzenie listy id zdjec
        int[] imageIDs = {
                R.drawable.karty_01, R.drawable.karty_02, R.drawable.karty_03, R.drawable.karty_04,
                R.drawable.karty_05, R.drawable.karty_06, R.drawable.karty_07, R.drawable.karty_08
        };
        //wypelnianie listy zdjec za pomoca listy id zdjec x2
        for (int imageID : imageIDs) {
            Drawable image = ContextCompat.getDrawable(this, imageID);
            images.add(image);
            images.add(image);
        }

        Collections.shuffle(images);
        //tworzenie listy przyciskow
        buttons = new ArrayList<>();
        //tworzenie listy id przyciskow
        buttonIDs = new int[]{
                R.id.karta1, R.id.karta2, R.id.karta3, R.id.karta4,
                R.id.karta5, R.id.karta6, R.id.karta7, R.id.karta8,
                R.id.karta9, R.id.karta10, R.id.karta11, R.id.karta12,
                R.id.karta13, R.id.karta14, R.id.karta15, R.id.karta16
        };
        //wypelnianie listy przyciskow za pomoca listy id przyciskow
        for (int resID : buttonIDs) {
            ImageButton button = findViewById(resID);
            buttons.add(button);
        }

        //Obsluga przyciskow
        for (int i = 0; i < buttons.size(); i++) {
            ImageButton button = buttons.get(i);
            int index = i;
            button.setOnClickListener(v -> {
                Log.i("LogImage", String.valueOf(button.getDrawable()));
                Log.d("LogImage", String.valueOf(default_image));
                Drawable currentImage = images.get(index);
                button.setImageDrawable(currentImage);
                //logika gry
                if(buttonIsCheacked1 != null && buttonIsCheacked2 != null){
                    buttonIsCheacked1.setImageDrawable(default_image);
                    buttonIsCheacked2.setImageDrawable(default_image);
                    buttonIsCheacked1.setEnabled(true);
                    buttonIsCheacked2 = null;
                    buttonIsCheacked1 = button;
                    button.setImageDrawable(currentImage);
                    buttonIsCheacked1.setEnabled(false);
                }else {
                    if (buttonIsCheacked1 != null) {
                        buttonIsCheacked2 = button;

                        if (buttonIsCheacked1.getDrawable().getConstantState().equals(buttonIsCheacked2.getDrawable().getConstantState())) {
                            buttonIsCheacked1.setEnabled(false);
                            buttonIsCheacked2.setEnabled(false);
                            buttonIsCheacked1 = null;
                            buttonIsCheacked2 = null;
                            Toast.makeText(MainActivity.this, "Zgadles!", Toast.LENGTH_SHORT).show();
                            isFinish();
                        }else{
                            buttonIsCheacked1.setEnabled(true);
                        }
                        count++;
                        textView.setText("Licznik ruchow: "+count);
                    } else {
                        buttonIsCheacked1 = button;
                        buttonIsCheacked1.setEnabled(false);
                    }
                }
                if(finish){
                    Toast.makeText(MainActivity.this, "Wygrales! Twoja liczba ruchow to "+count, Toast.LENGTH_SHORT).show();
                }
            });
        }
        //Obsluga przycisku reset
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
            finish = false;
        });
    }
    public void isFinish(){
        //Sparaw
        for (int i = 0; i < buttons.size(); i++) {
            ImageButton button = buttons.get(i);
            if(String.valueOf(button.getDrawable()) == String.valueOf(default_image)){
                finish = true;
            }else{
                finish = false;
            }
        }
    }
}
