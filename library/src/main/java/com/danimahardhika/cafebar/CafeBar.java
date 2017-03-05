package com.danimahardhika.cafebar;

/*
 * CafeBar
 *
 * Copyright (c) 2017 Dani Mahardhika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.BoolRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("unused")
public class CafeBar {

    private Builder mBuilder;
    private Snackbar mSnackBar;

    private CafeBar(@NonNull Builder builder) {
        mBuilder = builder;

        View cafeBarLayout = mBuilder.mCustomView;
        if (cafeBarLayout == null) {
            LogUtil.d("CafeBar doesn't have customView, preparing it...");
            cafeBarLayout = CafeBarUtil.getBaseCafeBarView(mBuilder);
        } else {
            CafeBarUtil.adjustCustomView(mBuilder, cafeBarLayout);
        }

        mSnackBar = CafeBarUtil.getBaseSnackBar(cafeBarLayout, mBuilder);
        if (mSnackBar == null) {
            LogUtil.d("Error, base snackbar == null");
            mBuilder = null;
            return;
        }

        if (mBuilder.mPositiveText == null && mBuilder.mNegativeText == null) {
            LogUtil.d("CafeBar only contains neutral button");
            if (mBuilder.mNeutralText != null) {
                setAction(mBuilder.mNeutralText, mBuilder.mNeutralColor, mBuilder.mNeutralCallback);
            }
        } else {
            LogUtil.d("CafeBar contains positive or negative button");
            LinearLayout root = (LinearLayout) getCafeBarView();
            LinearLayout buttonBase = (LinearLayout) root.findViewById(R.id.cafebar_button_base);

            if (mBuilder.mNeutralText != null) {
                TextView neutral = (TextView) buttonBase.findViewById(R.id.cafebar_button_neutral);
                neutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mBuilder.mNeutralCallback != null) {
                            mBuilder.mNeutralCallback.OnClick(getCafeBar());
                            return;
                        }

                        dismiss();
                    }
                });
            }

            if (mBuilder.mNegativeText != null) {
                TextView negative = (TextView) buttonBase.findViewById(R.id.cafebar_button_negative);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mBuilder.mNegativeCallback != null) {
                            mBuilder.mNegativeCallback.OnClick(getCafeBar());
                            return;
                        }

                        dismiss();
                    }
                });
            }

            if (mBuilder.mPositiveText != null) {
                TextView positive = (TextView) buttonBase.findViewById(R.id.cafebar_button_positive);
                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mBuilder.mPositiveCallback != null) {
                            mBuilder.mPositiveCallback.OnClick(getCafeBar());
                            return;
                        }

                        dismiss();
                    }
                });
            }
        }
    }

    public static void enableLogging(boolean enableLogging) {
        LogUtil.sEnableLogging = enableLogging;
    }

    @NonNull
    public static CafeBar make(@NonNull Context context, @NonNull String content, @NonNull CafeBarDuration duration) {
        return prepareCafeBar(context, content, duration);
    }

    @NonNull
    public static CafeBar make(@NonNull Context context, @StringRes int res, @NonNull CafeBarDuration duration) {
        String string = context.getResources().getString(res);
        return prepareCafeBar(context, string, duration);
    }

    @NonNull
    public static CafeBar make(@NonNull View to, @NonNull String content, @NonNull CafeBarDuration duration) {
        return prepareCafeBar(to, content, duration);
    }

    @NonNull
    public static CafeBar make(@NonNull View to, @StringRes int res, @NonNull CafeBarDuration duration) {
        String string = to.getContext().getResources().getString(res);
        return prepareCafeBar(to, string, duration);
    }

    @NonNull
    private static CafeBar prepareCafeBar(@NonNull Context context, @NonNull String content,
                                   @NonNull CafeBarDuration duration) {
        CafeBar.Builder builder = new Builder(context);
        builder.content(content);
        builder.duration(duration.getDuration());
        if (duration == CafeBarDuration.INDEFINITE) {
            builder.autoDismiss(false);
        }
        return new CafeBar(builder);
    }

    @NonNull
    private static CafeBar prepareCafeBar(@NonNull View to, @NonNull String content,
                                          @NonNull CafeBarDuration duration) {
        Context context = to.getContext();
        if (context instanceof ContextThemeWrapper) {
            context = ((ContextThemeWrapper) context).getBaseContext();
        }

        CafeBar.Builder builder = new Builder(context);
        builder.to(to);
        builder.content(content);
        builder.duration(duration.getDuration());
        if (duration == CafeBarDuration.INDEFINITE) {
            builder.autoDismiss(false);
        }
        return new CafeBar(builder);
    }

    public void setAction(@NonNull String action, @Nullable CafeBarCallback callback) {
        setButtonAction(action, mBuilder.mTheme.getTitleColor(), callback);
    }

    public void setAction(@NonNull String action, int color, @Nullable CafeBarCallback callback) {
        setButtonAction(action, color, callback);
    }

    public void setAction(@StringRes int res, @Nullable CafeBarCallback callback) {
        String string = mBuilder.mContext.getResources().getString(res);
        setButtonAction(string, mBuilder.mTheme.getTitleColor(), callback);
    }

    public void setAction(@StringRes int res, int color, @Nullable CafeBarCallback callback) {
        String string = mBuilder.mContext.getResources().getString(res);
        setButtonAction(string, color, callback);
    }

    private void setButtonAction(@NonNull String action, int color, @Nullable final CafeBarCallback callback) {
        if (mBuilder.mCustomView != null) {
            LogUtil.d("CafeBar has customView, setAction ignored.");
            return;
        }

        LogUtil.d("preparing action view");

        LinearLayout root = (LinearLayout) getCafeBarView();
        boolean longAction = CafeBarUtil.isLongAction(action);
        LinearLayout contentBase = (LinearLayout) root.findViewById(R.id.cafebar_content_base);

        //Todo: remove this, for testing purpose only
        //contentBase.setBackgroundColor(Color.GREEN);

        if (contentBase.getChildCount() > 1) {
            LogUtil.d("content container childView count > 1, setAction already set from builder via neutralText");
            return;
        }

        TextView content = (TextView) contentBase.findViewById(R.id.cafebar_content);

        int side = mBuilder.mContext.getResources().getDimensionPixelSize(R.dimen.cafebar_content_padding_side);
        int top = mBuilder.mContext.getResources().getDimensionPixelSize(R.dimen.cafebar_content_padding_top);
        int buttonPadding = mBuilder.mContext.getResources().getDimensionPixelSize(
                R.dimen.cafebar_button_padding);
        int bottom = 0;

        if (longAction) {
            bottom = buttonPadding;
            contentBase.setOrientation(LinearLayout.VERTICAL);
            content.setPadding(0, 0, buttonPadding, 0);
        } else {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) content.getLayoutParams();
            params.width = 0;
            params.weight = 1f;
            content.setLayoutParams(params);
        }

        int navBar = 0;
        LogUtil.d("fitSystemWindow: " +mBuilder.mFitSystemWindow);
        if (mBuilder.mFitSystemWindow && !mBuilder.mFloating) {
            navBar = CafeBarUtil.getNavigationBarHeight(mBuilder.mContext);
        }

        Configuration configuration = mBuilder.mContext.getResources().getConfiguration();
        boolean tabletMode = mBuilder.mContext.getResources().getBoolean(R.bool.cafebar_tablet_mode);

        if (tabletMode || configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (mBuilder.longContent()) {
                LogUtil.d("content has multi lines");
                root.setPadding(side, side, (side - buttonPadding), (side - bottom + navBar));
            } else if (longAction){
                LogUtil.d("content only 1 line with longAction");
                root.setPadding(side, top, (side - buttonPadding), (top - buttonPadding + navBar));
            } else {
                LogUtil.d("content only 1 line");
                root.setPadding(side, (top - buttonPadding), (side - buttonPadding), (top - buttonPadding + navBar));
            }
        } else {
            if (mBuilder.longContent()) {
                LogUtil.d("content has multi lines");
                root.setPadding(side, side, (side - buttonPadding + navBar), (side - bottom));
            } else if (longAction) {
                LogUtil.d("content only 1 line with longAction");
                root.setPadding(side, top, (side - buttonPadding + navBar), (top - buttonPadding));
            } else {
                LogUtil.d("content only 1 line");
                root.setPadding(side, (top - buttonPadding), (side - buttonPadding + navBar), (top - buttonPadding));
            }
        }

        TextView button = CafeBarUtil.getActionView(mBuilder, action, color);
        if (mBuilder.mNeutralTypeface != null) {
            LogUtil.d("action has custom typeface");
            button.setTypeface(mBuilder.mNeutralTypeface);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.OnClick(getCafeBar());
                    return;
                }

                LogUtil.d("callback = null, CafeBar dismissed");
                dismiss();
            }
        });

        contentBase.addView(button);
    }

    @NonNull
    private CafeBar getCafeBar() {
        return this;
    }

    @NonNull
    public View getCafeBarView() {
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) mSnackBar.getView();

        boolean tabletMode = mBuilder.mContext.getResources().getBoolean(R.bool.cafebar_tablet_mode);
        LogUtil.d("Tablet mode: " +tabletMode);

        LinearLayout linearLayout = (LinearLayout) snackBarLayout.getChildAt(0);

        if (tabletMode || mBuilder.mFloating) {
            CardView cardView = (CardView) linearLayout.getChildAt(0);
            return cardView.getChildAt(0);
        }

        if (mBuilder.mShowShadow) return linearLayout.getChildAt(1);
        return linearLayout.getChildAt(0);
    }

    public void dismiss() {
        if (mSnackBar == null) return;

        mSnackBar.dismiss();
    }

    public void show() {
        if (mSnackBar == null) return;

        mSnackBar.show();

        if (mBuilder.mSwipeToDismiss) return;

        if (mSnackBar.getView().getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
            mSnackBar.getView().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mSnackBar.getView().getViewTreeObserver().removeOnPreDrawListener(this);
                    ((CoordinatorLayout.LayoutParams) mSnackBar.getView().getLayoutParams()).setBehavior(null);
                    return true;
                }
            });
        }
    }

    @NonNull
    public static Builder builder(@NonNull Context context) {
        return new Builder(context);
    }

    @SuppressWarnings("unused")
    public static class Builder {

        private Context mContext;

        private View mView;
        private View mCustomView;

        private CafeBarTheme mTheme = CafeBarTheme.DARK;
        private CafeBarGravity mGravity = CafeBarGravity.CENTER;

        private int mDuration = CafeBarDuration.SHORT.getDuration();
        private int mMaxLines = 2;
        private int mPositiveColor = CafeBarTheme.DARK.getTitleColor();
        private int mNegativeColor = CafeBarTheme.DARK.getTitleColor();
        private int mNeutralColor = CafeBarTheme.DARK.getTitleColor();

        private boolean mLongContent = false;
        private boolean mAutoDismiss = true;
        private boolean mShowShadow = true;
        private boolean mFitSystemWindow = false;
        private boolean mFloating = false;
        private boolean mAdjustCustomView = false;
        private boolean mFilterIconColor = true;
        private boolean mSwipeToDismiss = true;

        private Typeface mContentTypeface;
        private Typeface mPositiveTypeface;
        private Typeface mNegativeTypeface;
        private Typeface mNeutralTypeface;
        private Drawable mIcon;

        private String mContent = "";
        private String mPositiveText = null;
        private String mNegativeText = null;
        private String mNeutralText = null;

        private CafeBarCallback mPositiveCallback;
        private CafeBarCallback mNegativeCallback;
        private CafeBarCallback mNeutralCallback;

        public Builder(@NonNull Context context) {
            mContext = context;
        }

        public Builder to(@Nullable View view) {
            mView = view;
            return this;
        }

        public Builder customView(@LayoutRes int res) {
            mCustomView = View.inflate(mContext, res, null);
            return this;
        }

        public Builder customView(@Nullable View customView) {
            mCustomView = customView;
            return this;
        }

        public Builder customView(@LayoutRes int res, boolean adjustCustomView) {
            mCustomView = View.inflate(mContext, res, null);
            mAdjustCustomView = adjustCustomView;
            return this;
        }
        public Builder customView(@Nullable View customView, boolean adjustCustomView) {
            mCustomView = customView;
            mAdjustCustomView = adjustCustomView;
            return this;
        }

        public Builder content(@StringRes int res) {
            if (mContext == null) return this;

            mContent = mContext.getResources().getString(res);
            return this;
        }

        public Builder content(@NonNull String content) {
            mContent = content;
            return this;
        }

        public Builder maxLines(@IntRange (from = 1, to = 6) int maxLines) {
            mMaxLines = maxLines;
            return this;
        }

        public Builder duration(@IntRange (from = 1, to = 10000) int duration) {
            mDuration = duration;
            return this;
        }

        public Builder theme(@NonNull CafeBarTheme theme) {
            mTheme = theme;
            mPositiveColor = mNegativeColor = mNeutralColor = mTheme.getTitleColor();
            return this;
        }

        public Builder icon(@Nullable Drawable icon) {
            mIcon = icon;
            return this;
        }

        public Builder icon(@Nullable Bitmap icon) {
            mIcon = CafeBarUtil.toDrawable(mContext, icon);
            return this;
        }

        public Builder icon(@DrawableRes int res) {
            mIcon = CafeBarUtil.getDrawable(mContext, res);
            return this;
        }

        public Builder icon(@Nullable Drawable icon, boolean filterIconColor) {
            mIcon = icon;
            mFilterIconColor = filterIconColor;
            return this;
        }

        public Builder icon(@Nullable Bitmap icon, boolean filterIconColor) {
            mIcon = CafeBarUtil.toDrawable(mContext, icon);
            mFilterIconColor = filterIconColor;
            return this;
        }

        public Builder icon(@DrawableRes int res, boolean filterIconColor) {
            mIcon = CafeBarUtil.getDrawable(mContext, res);
            mFilterIconColor = filterIconColor;
            return this;
        }

        public Builder showShadow(@BoolRes int res) {
            mShowShadow = mContext.getResources().getBoolean(res);
            return this;
        }

        public Builder showShadow(boolean showShadow) {
            mShowShadow = showShadow;
            return this;
        }

        public Builder autoDismiss(@BoolRes int res) {
            mAutoDismiss = mContext.getResources().getBoolean(res);
            return this;
        }

        public Builder autoDismiss(boolean autoDismiss) {
            mAutoDismiss = autoDismiss;
            return this;
        }

        public Builder swipeToDismiss(@BoolRes int res) {
            mSwipeToDismiss = mContext.getResources().getBoolean(res);
            return this;
        }

        public Builder swipeToDismiss(boolean swipeToDismiss) {
            mSwipeToDismiss = swipeToDismiss;
            return this;
        }

        public Builder floating(@BoolRes int res) {
            mFloating = mContext.getResources().getBoolean(res);
            return this;
        }

        public Builder floating(boolean floating) {
            mFloating = floating;
            return this;
        }

        public Builder gravity(@NonNull CafeBarGravity gravity) {
            mGravity = gravity;
            return this;
        }

        public Builder fitSystemWindow(@BoolRes int res) {
            mFitSystemWindow = mContext.getResources().getBoolean(res);
            return this;
        }

        public Builder fitSystemWindow(boolean fitSystemWindow) {
            mFitSystemWindow = fitSystemWindow;
            return this;
        }

        public Builder contentTypeface(@Nullable Typeface typeface) {
            mContentTypeface = typeface;
            return this;
        }

        public Builder positiveTypeface(@Nullable Typeface positiveTypeface) {
            mPositiveTypeface = positiveTypeface;
            return this;
        }

        public Builder negativeTypeface(@Nullable Typeface negativeTypeface) {
            mNegativeTypeface = negativeTypeface;
            return this;
        }

        public Builder neutralTypeface(@Nullable Typeface neutralTypeface) {
            mNeutralTypeface = neutralTypeface;
            return this;
        }

        public Builder positiveColor(int positiveColor) {
            mPositiveColor = CafeBarUtil.getColor(mContext, positiveColor);
            return this;
        }

        public Builder negativeColor(int negativeColor) {
            mNegativeColor = CafeBarUtil.getColor(mContext, negativeColor);
            return this;
        }

        public Builder neutralColor(int neutralColor) {
            mNeutralColor = CafeBarUtil.getColor(mContext, neutralColor);
            return this;
        }

        public Builder positiveText(@StringRes int res) {
            mPositiveText = mContext.getResources().getString(res);
            return this;
        }

        public Builder positiveText(@NonNull String positiveText) {
            mPositiveText = positiveText;
            return this;
        }

        public Builder negativeText(@StringRes int res) {
            mNegativeText = mContext.getResources().getString(res);
            return this;
        }

        public Builder negativeText(@NonNull String negativeText) {
            mNegativeText = negativeText;
            return this;
        }

        public Builder neutralText(@StringRes int res) {
            mNeutralText = mContext.getResources().getString(res);
            return this;
        }

        public Builder neutralText(@NonNull String neutralText) {
            mNeutralText = neutralText;
            return this;
        }

        public Builder onPositive(@Nullable CafeBarCallback positiveCallback) {
            mPositiveCallback = positiveCallback;
            return this;
        }

        public Builder onNegative(@Nullable CafeBarCallback negativeCallback) {
            mNegativeCallback = negativeCallback;
            return this;
        }

        public Builder onNeutral(@Nullable CafeBarCallback neutralCallback) {
            mNeutralCallback = neutralCallback;
            return this;
        }

        void longContent(boolean longContent) {
            mLongContent = longContent;
        }

        boolean longContent() {
            return mLongContent;
        }

        public CafeBar build() {
            return new CafeBar(this);
        }

        @NonNull
        Context context() {
            return mContext;
        }

        @Nullable
        View to() {
            return mView;
        }

        @Nullable
        View customView() {
            return mCustomView;
        }

        boolean adjustCustomView() {
            return mAdjustCustomView;
        }

        @Nullable
        Typeface contentTypeface() {
            return mContentTypeface;
        }

        @NonNull
        CafeBarTheme theme() {
            return mTheme;
        }

        int duration() {
            return mDuration;
        }

        boolean autoDismiss() {
            return mAutoDismiss;
        }

        boolean showShadow() {
            return mShowShadow;
        }

        boolean fitSystemWindow() {
            return mFitSystemWindow;
        }

        boolean floating() {
            return mFloating;
        }

        @NonNull
        CafeBarGravity gravity() {
            return mGravity;
        }

        @Nullable
        Drawable icon() {
            return mIcon;
        }

        boolean filterIconColor() {
            return mFilterIconColor;
        }

        @NonNull
        String content() {
            return mContent;
        }

        int maxLines() {
            return mMaxLines;
        }

        int positiveColor() {
            return mPositiveColor;
        }

        int negativeColor() {
            return mNegativeColor;
        }

        int neutralColor() {
            return mNeutralColor;
        }

        @Nullable
        Typeface positiveTypeface() {
            return mPositiveTypeface;
        }

        @Nullable
        Typeface negativeTypeface() {
            return mNegativeTypeface;
        }

        @Nullable
        Typeface neutralTypeface() {
            return mNeutralTypeface;
        }

        @Nullable
        String positiveText() {
            return mPositiveText;
        }

        @Nullable
        String negativeText() {
            return mNegativeText;
        }

        @Nullable
        String neutralText() {
            return mNeutralText;
        }
    }
}
