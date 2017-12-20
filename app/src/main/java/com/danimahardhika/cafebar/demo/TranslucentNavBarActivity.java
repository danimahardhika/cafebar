package com.danimahardhika.cafebar.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import android.widget.Toast;
import com.danimahardhika.cafebar.CafeBar;
import com.danimahardhika.cafebar.CafeBarTheme;

public class TranslucentNavBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent_navbar);

        //Under Navbar
        findViewById(R.id.under_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .content(R.string.demo_text)
                        .neutralText("Under")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //Above Navbar
        findViewById(R.id.above_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .content(R.string.demo_text)
                        .fitSystemWindow()
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //Above Navbar move Fab
        findViewById(R.id.above_navbar_move_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .to(findViewById(R.id.coordinator))
                        .content(R.string.demo_text)
                        .fitSystemWindow()
                        .swipeToDismiss(false)
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //Floating Under Navbar
        findViewById(R.id.floating_under_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .content(R.string.demo_text)
                        .floating(true)
                        .neutralText("Under")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //Floating Above Navbar
        findViewById(R.id.floating_above_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .content(R.string.demo_text)
                        .floating(true)
                        .fitSystemWindow()
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //Floating Above Navbar Move Fab
        findViewById(R.id.floating_above_navbar_move_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .to(findViewById(R.id.coordinator))
                        .content(R.string.demo_text)
                        .floating(true)
                        .fitSystemWindow()
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //With Custom View
        findViewById(R.id.with_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(TranslucentNavBarActivity.this)
                        .customView(R.layout.cafebar_custom_layout)
                        .fitSystemWindow()
                        .autoDismiss(false);

                final CafeBar cafeBar = builder.build();
                View v = cafeBar.getView();

                AppCompatButton button = (AppCompatButton) v.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cafeBar.dismiss();
                    }
                });
                cafeBar.show();
            }
        });

        //Floating With Custom View Move Fab
        findViewById(R.id.floating_with_custom_view_move_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(TranslucentNavBarActivity.this)
                        .customView(R.layout.cafebar_custom_layout)
                        .to(findViewById(R.id.coordinator))
                        .fitSystemWindow()
                        .floating(true)
                        .autoDismiss(false);

                final CafeBar cafeBar = builder.build();
                View v = cafeBar.getView();

                AppCompatButton button = (AppCompatButton) v.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cafeBar.dismiss();
                    }
                });
                cafeBar.show();
            }
        });

        //Floating Swipeable with callback
        findViewById(R.id.themed_long_action_swipe_to_dismiss_with_callback).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                    //Set theme first, then set positive, negative, or neutral color
                    .theme(CafeBarTheme.CLEAR_BLACK)
                    .content(R.string.demo_text)
                    .swipeToDismiss(true)
                    .floating(true)
                    .to(findViewById(R.id.coordinator))
                    .autoDismiss(false)
                    .maxLines(4)
                    .callback(new Snackbar.Callback() {
                        @Override public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);
                            Toast.makeText(TranslucentNavBarActivity.this, "CafeBar dismissed", Toast.LENGTH_LONG).show();
                        }

                        @Override public void onShown(Snackbar sb) {
                            super.onShown(sb);
                            Toast.makeText(TranslucentNavBarActivity.this, "CafeBar shown", Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
            }
        });
    }
}
