package com.danimahardhika.cafebar.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.danimahardhika.cafebar.CafeBar;
import com.danimahardhika.cafebar.CafeBarDuration;

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
                        .build().show();
            }
        });

        //Above Navbar
        findViewById(R.id.above_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .content(R.string.demo_text)
                        .fitSystemWindow(true)
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .build().show();
            }
        });

        //Above Navbar move Fab
        findViewById(R.id.above_navbar_move_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .to(findViewById(R.id.coordinator))
                        .content(R.string.demo_text)
                        .fitSystemWindow(true)
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .build().show();
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
                        .build().show();
            }
        });

        //Floating Above Navbar
        findViewById(R.id.floating_above_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(TranslucentNavBarActivity.this)
                        .content(R.string.demo_text)
                        .floating(true)
                        .fitSystemWindow(true)
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .build().show();
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
                        .fitSystemWindow(true)
                        .neutralText("Above")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .build().show();
            }
        });

        //With Custom View
        findViewById(R.id.with_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(TranslucentNavBarActivity.this)
                        .customView(R.layout.cafebar_custom_layout)
                        .fitSystemWindow(true)
                        .autoDismiss(false);

                final CafeBar cafeBar = builder.build();
                View v = cafeBar.getCafeBarView();

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

        //With Custom View Adjusted
        findViewById(R.id.with_custom_view_adjusted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(TranslucentNavBarActivity.this)
                        .customView(R.layout.cafebar_custom_layout, true)
                        .fitSystemWindow(true)
                        .autoDismiss(false);

                final CafeBar cafeBar = builder.build();
                View v = cafeBar.getCafeBarView();

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

        //With Custom View Adjusted Move Fab
        findViewById(R.id.with_custom_view_adjusted_move_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(TranslucentNavBarActivity.this)
                        .customView(R.layout.cafebar_custom_layout, true)
                        .to(findViewById(R.id.coordinator))
                        .fitSystemWindow(true)
                        .autoDismiss(false);

                final CafeBar cafeBar = builder.build();
                View v = cafeBar.getCafeBarView();

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

        //Floating With Custom View Adjusted Move Fab
        findViewById(R.id.floating_with_custom_view_adjusted_move_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(TranslucentNavBarActivity.this)
                        .customView(R.layout.cafebar_custom_layout, true)
                        .to(findViewById(R.id.coordinator))
                        .fitSystemWindow(true)
                        .floating(true)
                        .autoDismiss(false);

                final CafeBar cafeBar = builder.build();
                View v = cafeBar.getCafeBarView();

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
    }
}
