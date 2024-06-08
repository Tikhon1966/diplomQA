package ru.iteco.fmhandroid.ui.screenElements;

import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class SplashElements {
    public static Matcher<View> getSplashscreenImage() {
        return ViewMatchers.withId(R.id.splashscreen_image_view);
    }
}
