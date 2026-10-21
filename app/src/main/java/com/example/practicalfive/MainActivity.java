package com.example.practicalfive;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class MainActivity extends Activity {
    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.MUNICIPALITY";
    private final TextProcessor processor = new TextProcessor();
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        WindowInsetsHelper.apply(this);
        EditText input = findViewById(R.id.municipality_input);
        input.setOnEditorActionListener((view, actionId, event) -> {
            boolean enter = event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
            if (enter && event.getAction() != KeyEvent.ACTION_UP) return true;
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE || enter) {
                String name = processor.processMunicipality(input.getText().toString());
                if (name == null) {
                    input.setError(getString(input.getText().toString().trim().isEmpty()
                            ? R.string.empty_error : R.string.invalid_error));
                } else {
                    input.setError(null);
                    Intent intent = new Intent(this, Municipality.class);
                    intent.putExtra(EXTRA_MUNICIPALITY, name);
                    startActivity(intent);
                }
                return true;
            }
            return false;
        });
    }
}
