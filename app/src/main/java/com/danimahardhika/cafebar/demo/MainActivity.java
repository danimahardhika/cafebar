package com.danimahardhika.cafebar.demo;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.widget.Toast;

import com.danimahardhika.cafebar.CafeBar;
import com.danimahardhika.cafebar.CafeBarCallback;
import com.danimahardhika.cafebar.CafeBarDuration;
import com.danimahardhika.cafebar.CafeBarGravity;
import com.danimahardhika.cafebar.CafeBarTheme;

import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enable logging, default is false
        //This is just for my testing purpose actually
        CafeBar.enableLogging(true);

        //Simple Single Line
        findViewById(R.id.simple_single_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.make(MainActivity.this, "Single Line...", CafeBarDuration.SHORT).show();
            }
        });

        //Simple Multi Lines
        findViewById(R.id.simple_multi_lines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.make(MainActivity.this, R.string.demo_text, CafeBarDuration.SHORT).show();
            }
        });

        //Simple Themed
        findViewById(R.id.simple_themed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .theme(CafeBarTheme.LIGHT)
                        .content(R.string.demo_text)
                        .show();
            }
        });

        //Simple Themed with Action
        findViewById(R.id.simple_themed_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .theme(CafeBarTheme.LIGHT)
                        .content("Lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
                        .neutralText("Very Long Action Text")
                        .buttonColor(Color.parseColor("#03A9F4"))
                        .show();
            }
        });

        //Simple with Action
        findViewById(R.id.simple_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar cafeBar = CafeBar.make(MainActivity.this, "CafeBar with Action", CafeBarDuration.INDEFINITE);
                cafeBar.setAction("Action", Color.parseColor("#03A9F4"), new CafeBarCallback() {
                    @Override
                    public void OnClick(@NonNull CafeBar cafeBar) {
                        Toast.makeText(MainActivity.this, "CafeBar dismissed", Toast.LENGTH_LONG).show();

                        //Dismiss CafeBar when action pressed
                        cafeBar.dismiss();
                    }
                });
                //Don't forget to show
                cafeBar.show();
            }
        });

        //Simple with Icon
        findViewById(R.id.simple_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .icon(R.drawable.ic_launcher)
                        .content(R.string.demo_text)
                        .show();
            }
        });

        //Simple with Icon Unfiltered
        findViewById(R.id.simple_icon_unfiltered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .icon(R.drawable.ic_launcher, false)
                        .content(R.string.demo_text)
                        .show();
            }
        });

        //Simple with Icon Action
        findViewById(R.id.simple_icon_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .icon(R.drawable.ic_action_info)
                        .content("CafeBar with Icon and Action")
                        .neutralText("Action")
                        .show();
            }
        });

        //Simple with Positive Button
        findViewById(R.id.simple_button_positive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .content(R.string.demo_text)
                        .positiveText("Positive")
                        .show();
            }
        });

        //Themed with Long Text Action no Shadow
        findViewById(R.id.themed_long_action_noshadow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        //Set theme first, then set positive, negative, or neutral color
                        .theme(CafeBarTheme.CLEAR_BLACK)
                        .content(R.string.demo_text)
                        .showShadow(false)
                        .autoDismiss(false)
                        .neutralText("Action with Long Text")
                        .maxLines(4)
                        .neutralColor(Color.parseColor("#F44336"))
                        .onNeutral(new CafeBarCallback() {
                            @Override
                            public void OnClick(@NonNull CafeBar cafeBar) {
                                Toast.makeText(MainActivity.this, "CafeBar dismissed", Toast.LENGTH_LONG).show();

                                //Dismiss CafeBar when action pressed
                                cafeBar.dismiss();
                            }
                        }).show();
            }
        });

        //Themed with Custom Background Color
        findViewById(R.id.themed_custom_background_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(MainActivity.this)
                        .content(R.string.demo_text)
                        .duration(2000);

                //--- You can use custom theme in release 1.0.7
                //Text color (content and buttons) automatically set
                builder.theme(CafeBarTheme.Custom(Color.parseColor("#F44336")));

                CafeBar cafeBar = builder.build();
                //--- Old method
                //View v = cafeBar.getCafeBarView();
                //v.setBackgroundColor(Color.parseColor("#455A64"));

                cafeBar.show();
            }
        });

        //Themed with Icon and Button Callback
        findViewById(R.id.themed_button_positive_negative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .theme(CafeBarTheme.LIGHT)
                        .icon(R.drawable.ic_action_info)
                        .content(R.string.demo_text)
                        .maxLines(4)
                        .autoDismiss(false)
                        .positiveText("Positive")
                        .positiveColor(Color.parseColor("#4CAF50"))
                        .onPositive(new CafeBarCallback() {
                            @Override
                            public void OnClick(@NonNull CafeBar cafeBar) {
                                Toast.makeText(MainActivity.this, "Positive pressed", Toast.LENGTH_SHORT).show();

                                //Dismiss CafeBar when action pressed
                                cafeBar.dismiss();
                            }
                        })
                        .negativeText("Negative")
                        .negativeColor(Color.parseColor("#F44336"))
                        .onNegative(new CafeBarCallback() {
                            @Override
                            public void OnClick(@NonNull CafeBar cafeBar) {
                                Toast.makeText(MainActivity.this, "Negative pressed", Toast.LENGTH_SHORT).show();

                                //Dismiss CafeBar when action pressed
                                cafeBar.dismiss();
                            }
                        })
                        .show();
            }
        });

        //Floating
        findViewById(R.id.floating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .content("Floating CafeBar")
                        .floating(true)
                        .duration(CafeBarDuration.SHORT.getDuration())
                        .neutralText("Floating")
                        .neutralColor(Color.parseColor("#EEFF41"))
                        .show();
            }
        });

        //Themed Floating with Custom Gravity
        findViewById(R.id.themed_floating_gravity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .content("Floating CafeBar")
                        .theme(CafeBarTheme.LIGHT)
                        .floating(true)
                        .gravity(CafeBarGravity.START)
                        .autoDismiss(false)
                        .neutralText("Floating")
                        .neutralColor(Color.parseColor("#FF4081"))
                        .show();
            }
        });

        //Themed Floating with Long Text Icon All Button Set
        findViewById(R.id.floating_long_text_icon_all_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .icon(R.drawable.ic_action_info)
                        .content(R.string.demo_text)
                        .floating(true)
                        .autoDismiss(false)
                        .neutralText("Neutral")
                        .neutralColor(Color.parseColor("#66FFFFFF"))
                        .onNeutral(new CafeBarCallback() {
                            @Override
                            public void OnClick(@NonNull CafeBar cafeBar) {
                                Toast.makeText(MainActivity.this, "Neutral pressed", Toast.LENGTH_SHORT).show();

                                //Dismiss CafeBar when action pressed
                                cafeBar.dismiss();
                            }
                        })
                        .positiveText("Positive")
                        .positiveColor(Color.parseColor("#EEFF41"))
                        .onPositive(new CafeBarCallback() {
                            @Override
                            public void OnClick(@NonNull CafeBar cafeBar) {
                                Toast.makeText(MainActivity.this, "Positive pressed", Toast.LENGTH_SHORT).show();

                                //Dismiss CafeBar when action pressed
                                cafeBar.dismiss();
                            }
                        })
                        .negativeText("Negative")
                        .negativeColor(Color.parseColor("#66FFFFFF"))
                        .onNegative(new CafeBarCallback() {
                            @Override
                            public void OnClick(@NonNull CafeBar cafeBar) {
                                Toast.makeText(MainActivity.this, "Negative pressed", Toast.LENGTH_SHORT).show();

                                //Dismiss CafeBar when action pressed
                                cafeBar.dismiss();
                            }
                        })
                        .show();
            }
        });

        //With Custom View
        findViewById(R.id.with_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(MainActivity.this)
                        .customView(R.layout.cafebar_custom_layout)
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
                CafeBar.Builder builder = new CafeBar.Builder(MainActivity.this)
                        .customView(R.layout.cafebar_custom_layout, true)
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

        //Floating With Custom View Adjusted
        findViewById(R.id.floating_with_custom_view_adjusted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.Builder builder = new CafeBar.Builder(MainActivity.this)
                        .customView(R.layout.cafebar_custom_layout, true)
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

        //Spannable Typeface
        findViewById(R.id.spannable_typeface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String join1 = "With";
                String join2 ="Spannable Typeface";
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(join1)
                        .append(" ")
                        .append(join2);
                CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(
                        TypefaceUtils.load(MainActivity.this.getAssets(), "fonts/RobotoMono-Bold.ttf"));
                builder.setSpan(typefaceSpan, join1.length() + 1, builder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                CafeBar.builder(MainActivity.this)
                        .content(builder)
                        .contentTypeface("RobotoMono-Regular.ttf")
                        .floating(true)
                        .gravity(CafeBarGravity.START)
                        .show();
            }
        });

        //Custom Typeface
        findViewById(R.id.custom_typeface).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CafeBar.builder(MainActivity.this)
                        .content("With Custom Typeface")
                        .typeface("RobotoMono-Regular.ttf", "RobotoMono-Bold.ttf")
                        .floating(true)
                        .gravity(CafeBarGravity.START)
                        .autoDismiss(false)
                        .neutralText("Dismiss")
                        .neutralColor(Color.parseColor("#EEFF41"))
                        .show();
            }
        });

        //Activity Translucent Navbar
        findViewById(R.id.activity_trasnlucent_navbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, TranslucentNavBarActivity.class));
            }
        });
    }
}
