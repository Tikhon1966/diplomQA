package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.data.DataHelper.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class LoveElements {
    public static ViewInteraction missionName = onView((withId(R.id.our_mission_title_text_view)));
    public static ViewInteraction missionList = onView(withId(R.id.our_mission_item_list_recycler_view));
    public static ViewInteraction missionConstraintLayout = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
            childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));

    public static Matcher<View> getDescriptionText() {
        return ViewMatchers.withId(R.id.our_mission_item_description_text_view);
    }
}
