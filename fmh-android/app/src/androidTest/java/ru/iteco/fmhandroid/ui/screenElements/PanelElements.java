package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class PanelElements {
    public static ViewInteraction panelName = onView(withText("Control panel"));
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public static ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction newsTitleText =
            onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public static ViewInteraction newsDescriptionText =
            onView(withIndex(withId(R.id.news_item_description_text_view), 0));
    public static ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public static ViewInteraction statusActive =
            onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public static ViewInteraction statusNotActive =
            onView(withIndex(withId(R.id.news_item_published_text_view), 0));

    public static Matcher<View> getNewsPublicationDate() {
        return ViewMatchers.withId(R.id.news_item_publication_date_text_view);
    }

    public static Matcher<View> getButtonEditNews() {
        return ViewMatchers.withId(R.id.edit_news_item_image_view);
    }

    public static Matcher<View> getButtonDeleteNews() {
        return ViewMatchers.withId(R.id.delete_news_item_image_view);
    }
}
