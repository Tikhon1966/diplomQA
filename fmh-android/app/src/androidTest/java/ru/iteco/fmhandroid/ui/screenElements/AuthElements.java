package ru.iteco.fmhandroid.ui.screenElements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AuthElements {
    public static ViewInteraction authText =
            onView(withText("Authorization"));
    public static ViewInteraction loginText =
            onView(withHint("Login"));
    public static ViewInteraction passText =
            onView(withHint("Password"));
    public static ViewInteraction signButton =
            onView(withId(R.id.enter_button));

    public void loadAuthPage() {
        Allure.step("Загрузка страницы авторизации");
        elementWaiting(withId(R.id.enter_button), 5000);
    }
}
