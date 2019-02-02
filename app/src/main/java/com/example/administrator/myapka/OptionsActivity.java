package com.example.administrator.myapka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

public class OptionsActivity extends AppCompatActivity {

    private Spinner languageSpinner;
    private String selectedLanguage;

    private Switch styleSwitch;
    private boolean darkStyleOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        selectedLanguage = sharedPref.getString(getString(R.string.languageKey), "pl");
        darkStyleOn = sharedPref.getBoolean(getString(R.string.themeKey), false);
        Log.d("MyActivity", "LoadedLanguage: "+selectedLanguage);
        Log.d("MyActivity", "LoadedThemeDark?: "+darkStyleOn);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

      languageSpinner = findViewById(R.id.change_language);
      AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguage = languageSpinner.getSelectedItem().toString();
          }
          @Override
          public void onNothingSelected(AdapterView<?> arg0) {
          }
      };
      languageSpinner.setOnItemSelectedListener(listener);

      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      styleSwitch = findViewById(R.id.colour);
      styleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              Log.v("Switch State=", ""+isChecked);
              darkStyleOn = isChecked;
              SetTheme(darkStyleOn);
          }
      });
      styleSwitch.setChecked(darkStyleOn);
      languageSpinner.setSelection(GetSelectionNumberForLanguage(selectedLanguage));

        findViewById(R.id.save).setOnClickListener(v -> {
            SaveOptions();
            startActivity(new Intent(this, MainActivity.class));
        });

    }

    private int GetSelectionNumberForLanguage(String selectedLanguage) {
        switch (selectedLanguage) {
            case "Polish":
                return 0;
            case "English":
                return 1;
            default:
                return 0;
        }
    }

    private void SaveOptions() {
        //ustaw wybrany język
        Log.d("MyActivity", "Selected language:" +selectedLanguage);
        UpdateLanguage(selectedLanguage);
        //ustaw wybrany kolor stylu
        //set theme

        //zapisywanie
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.languageKey), selectedLanguage);
        editor.putBoolean(getString(R.string.themeKey), darkStyleOn);
        editor.commit();
    }

    private void SetTheme(boolean darkThemeOn) {
        if (darkThemeOn) {
            Log.d("MyActivity","DarkThemeOn");
            this.setTheme(R.style.AppThemeDark);
//            ((ViewGroup) this.getParent()).setBackgroundColor(darkThemeOn ?
  //                  Color.BLACK : Color.WHITE);
        }
        else {
            Log.d("MyActivity","LightThemeOn");
            this.setTheme(R.style.AppTheme);
        }
    }

    private void UpdateLanguage(String language) {
        Locale locale = new Locale("pl");
        Locale.setDefault(locale);
        Log.d("MyActivity", "locale"+locale);

        Resources res = this.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
