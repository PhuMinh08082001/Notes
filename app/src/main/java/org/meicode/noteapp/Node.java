package org.meicode.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;

public class Node extends AppCompatActivity {
    EditText ed_typing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);

        SharedPreferences sharedPreferences = getApplication(). getSharedPreferences("org.meicode.noteapp", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position" , 0);
        ed_typing = (EditText) findViewById(R.id.ed_typing);
        ed_typing.setText(intent.getStringExtra("data"));

        ed_typing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.listNode.set(position, ed_typing.getText().toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        HashSet<String> set = new HashSet<>(MainActivity.listNode);
        sharedPreferences.edit().putStringSet("listnodes" , set).apply();
}
}