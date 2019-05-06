package com.newyear.tablayout.mytablayout;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A tab in this layout. Instances can be created via {@link ##com.newyear.android.widget.TabLayout.newTab()}.
 */
public class Tab{

    /**
     * An invalid position for a tab.
     *
     * @see #getPosition()
     */
    public static final int INVALID_POSITION = -1;

    private Object mTag;
    private Drawable mIcon;
    private CharSequence mText;
    private CharSequence mContentDesc;
    private int mPosition = INVALID_POSITION;
    private View mCustomView;

    TabLayout mParent;
    TabLayout.TabView mView;

    Tab() {
        // Private constructor
    }

    /**
     * @return This Tab's tag object.
     */
    @Nullable
    public Object getTag() {
        return mTag;
    }

    /**
     * Give this Tab an arbitrary object to hold for later use.
     *
     * @param tag Object to store
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setTag(@Nullable Object tag) {
        mTag = tag;
        return this;
    }


    /**
     * Returns the custom view used for this tab.
     *
     * @see #setCustomView(View)
     * @see #setCustomView(int)
     */
    @Nullable
    public View getCustomView() {
        return mCustomView;
    }

    /**
     * Set a custom view to be used for this tab.
     * <p>
     * If the provided view contains a {@link TextView} with an ID of
     * {@link android.R.id#text1} then that will be updated with the value given
     * to {@link #setText(CharSequence)}. Similarly, if this layout contains an
     * {@link ImageView} with ID {@link android.R.id#icon} then it will be updated with
     * the value given to {@link #setIcon(Drawable)}.
     * </p>
     *
     * @param view Custom view to be used as a tab.
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setCustomView(@Nullable View view) {
        mCustomView = view;
        updateView();
        return this;
    }

    /**
     * Set a custom view to be used for this tab.
     * <p>
     * If the inflated layout contains a {@link TextView} with an ID of
     * {@link android.R.id#text1} then that will be updated with the value given
     * to {@link #setText(CharSequence)}. Similarly, if this layout contains an
     * {@link ImageView} with ID {@link android.R.id#icon} then it will be updated with
     * the value given to {@link #setIcon(Drawable)}.
     * </p>
     *
     * @param resId A layout resource to inflate and use as a custom tab view
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setCustomView(@LayoutRes int resId) {
        final LayoutInflater inflater = LayoutInflater.from(mView.getContext());
        return setCustomView(inflater.inflate(resId, mView, false));
    }

    /**
     * Return the icon associated with this tab.
     *
     * @return The tab's icon
     */
    @Nullable
    public Drawable getIcon() {
        return mIcon;
    }

    /**
     * Return the current position of this tab in the action bar.
     *
     * @return Current position, or {@link #INVALID_POSITION} if this tab is not currently in
     * the action bar.
     */
    public int getPosition() {
        return mPosition;
    }

    void setPosition(int position) {
        mPosition = position;
    }

    /**
     * Return the text of this tab.
     *
     * @return The tab's text
     */
    @Nullable
    public CharSequence getText() {
        return mText;
    }

    /**
     * Set the icon displayed on this tab.
     *
     * @param icon The drawable to use as an icon
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setIcon(@Nullable Drawable icon) {
        mIcon = icon;
        updateView();
        return this;
    }

    /**
     * Set the icon displayed on this tab.
     *
     * @param resId A resource ID referring to the icon that should be displayed
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setIcon(@DrawableRes int resId) {
        if (mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return setIcon(AppCompatResources.getDrawable(mParent.getContext(), resId));
    }

    /**
     * Set the text displayed on this tab. Text may be truncated if there is not room to display
     * the entire string.
     *
     * @param text The text to display
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setText(@Nullable CharSequence text) {
        mText = text;
        updateView();
        return this;
    }

    /**
     * Set the text displayed on this tab. Text may be truncated if there is not room to display
     * the entire string.
     *
     * @param resId A resource ID referring to the text that should be displayed
     * @return The current instance for call chaining
     */
    @NonNull
    public Tab setText(@StringRes int resId) {
        if (mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return setText(mParent.getResources().getText(resId));
    }

    /**
     * Select this tab. Only valid if the tab has been added to the action bar.
     */
    public void select() {
        if (mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        mParent.selectTab(this);
    }

    /**
     * Returns true if this tab is currently selected.
     */
    public boolean isSelected() {
        if (mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return mParent.getSelectedTabPosition() == mPosition;
    }

    /**
     * Set a description of this tab's content for use in accessibility support. If no content
     * description is provided the title will be used.
     *
     * @param resId A resource ID referring to the description text
     * @return The current instance for call chaining
     * @see #setContentDescription(CharSequence)
     * @see #getContentDescription()
     */
    @NonNull
    public Tab setContentDescription(@StringRes int resId) {
        if (mParent == null) {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return setContentDescription(mParent.getResources().getText(resId));
    }

    /**
     * Set a description of this tab's content for use in accessibility support. If no content
     * description is provided the title will be used.
     *
     * @param contentDesc Description of this tab's content
     * @return The current instance for call chaining
     * @see #setContentDescription(int)
     * @see #getContentDescription()
     */
    @NonNull
    public Tab setContentDescription(@Nullable CharSequence contentDesc) {
        mContentDesc = contentDesc;
        updateView();
        return this;
    }

    /**
     * Gets a brief description of this tab's content for use in accessibility support.
     *
     * @return Description of this tab's content
     * @see #setContentDescription(CharSequence)
     * @see #setContentDescription(int)
     */
    @Nullable
    public CharSequence getContentDescription() {
        return mContentDesc;
    }

    void updateView() {
        if (mView != null) {
            mView.update();
        }
    }

    void reset() {
        mParent = null;
        mView = null;
        mTag = null;
        mIcon = null;
        mText = null;
        mContentDesc = null;
        mPosition = INVALID_POSITION;
        mCustomView = null;
    }
}