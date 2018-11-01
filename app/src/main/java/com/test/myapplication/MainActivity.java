package com.test.myapplication;

import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final boolean USE_OFFSET_API = (Build.VERSION.SDK_INT >= 16)
            && (Build.VERSION.SDK_INT <= 19);
    private RelativeLayout mBodyLayout;
    private RelativeLayout snackBar;
    boolean bSnackShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBodyLayout = findViewById(R.id.parent);
        snackBar = findViewById(R.id.layout_snackbar);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bSnackShow = !bSnackShow;
                animIn(snackBar, bSnackShow, snackBar.getHeight());
                animIn(mBodyLayout, bSnackShow, snackBar.getHeight());
            }
        });
    }

    private void animIn(final View mView, boolean bRise, int delta) {
        final int viewHeight = mView.getHeight();
        if (USE_OFFSET_API) {
            ViewCompat.offsetTopAndBottom(mView, delta);
        } else {
            mView.setTranslationY(-delta);
        }
        final ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(bRise ? 0 : delta, bRise ? delta : 0);
        animator.setDuration(500);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private int mPreviousAnimatedIntValue = viewHeight;

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentAnimatedIntValue = (int) animator.getAnimatedValue();
                if (USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(mView,
                            currentAnimatedIntValue - mPreviousAnimatedIntValue);
                } else {
                    mView.setTranslationY(-currentAnimatedIntValue);
                }
                mPreviousAnimatedIntValue = currentAnimatedIntValue;
            }
        });
        animator.start();
    }
}
