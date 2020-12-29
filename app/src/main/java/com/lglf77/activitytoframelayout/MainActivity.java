package com.lglf77.activitytoframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Animation effectBottomAnimation, effectStretchAnimation, effectTopAnimation;
    LinearLayout containerLottie, containerInformation;
    FrameLayout showFrameLayoutContainer;
    Button showFrameLayout;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        effectBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top_anim);
        containerInformation = findViewById(R.id.linear_layout_information);
        containerInformation.setAnimation(effectBottomAnimation);

        containerLottie = findViewById(R.id.linear_layout_lottie);
        effectStretchAnimation = AnimationUtils.loadAnimation(this, R.anim.stretch_animation);
        containerLottie.setAnimation(effectStretchAnimation);

        showFrameLayoutContainer = findViewById(R.id.frame_layout);
        showFrameLayout = findViewById(R.id.btnFrameLayout);
        showFrameLayout.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {
                visible = !visible;
                showFrameLayoutContainer.setVisibility(visible ? View.VISIBLE : View.GONE);
                effectFragment();
            }
        });
    }

    public void effectFragment() {
        effectTopAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top_anim);
        showFrameLayoutContainer.setAnimation(effectTopAnimation);
        showFrameLayoutContainer.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {
                visible = !visible;
                showFrameLayoutContainer.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void fragmentHide() {
        count ++;
        if (count >= 1) {
            Intent intent = new Intent (this, MainActivity.class);
            FrameLayout showFrameLayoutContainer = (FrameLayout) findViewById (R.id.frame_layout);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.top_to_bottom_anim, Gravity.CENTER);
            finishAffinity();
        } else {
            // resetting the counter in 2s
            Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed (new Runnable () {
                @Override
                public void run () {
                    count = 0;
                }
            }, 1000);
        }
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            fragmentHide();
        } else {
            super.onBackPressed();
        }
    }
}

