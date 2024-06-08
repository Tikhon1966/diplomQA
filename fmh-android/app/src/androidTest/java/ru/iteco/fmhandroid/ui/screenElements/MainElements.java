package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.data.DataHelper.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class MainElements {
    public static ViewInteraction logoutText = onView(withText("Log out"));
    public static ViewInteraction mainText = onView(withText("Main"));
    public static ViewInteraction newsText = onView(withText("News"));
    public static ViewInteraction allNews = onView(withText("ALL NEWS"));
    public static ViewInteraction aboutText = onView(withText("About"));
    public static ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction missionButton = onView(withId(R.id.our_mission_image_button));
    public static ViewInteraction logoutButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    public static ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    public static ViewInteraction childNews = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), 0)));
    public static ViewInteraction newsBlockButton = onView(
            allOf(withId(R.id.expand_material_button),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")),
                            childAtPosition(withClassName(is("android.widget.LinearLayout")),
                                    withId(R.id.container_list_news_include_on_fragment_main),
                                    0), 4)));

    public static Matcher<View> getNewsDescriptionText() {
        return ViewMatchers.withId(R.id.news_item_description_text_view);
    }
}
