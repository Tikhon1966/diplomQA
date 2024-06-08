package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreateNewsElements {
    public static ViewInteraction title = onView(withHint("Title"));
    public static ViewInteraction category = onView(withHint("Category"));
    public static ViewInteraction description = onView(withHint("Description"));
    public static ViewInteraction publicationDate = onView(withHint("Publication date"));
    public static ViewInteraction creating = onView(withText("Creating"));
    public static ViewInteraction categoryText =
            onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction titleText = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction descriptionText =
            onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction timeText =
            onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction publishDateText =
            onView(withId(R.id.news_item_publish_date_text_input_edit_text));
}
