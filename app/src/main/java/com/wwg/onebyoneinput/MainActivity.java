package com.wwg.onebyoneinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.wwg.onebyoneinputlayout.OneByOneInputLayout;
import com.wwg.onebyoneinputlayout.OneByOneInputListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneByOneInputLayout inputLayout = findViewById(R.id.inputLayout);

        //设置输入模式
        inputLayout.getConfig().setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        //设置输入监听
        inputLayout.setInputListener(new OneByOneInputListener() {
            @Override
            public void getInputInfo(String info) {

                Toast.makeText(MainActivity.this, "输入信息 --> " + info, Toast.LENGTH_SHORT).show();

            }
        });

    }
}