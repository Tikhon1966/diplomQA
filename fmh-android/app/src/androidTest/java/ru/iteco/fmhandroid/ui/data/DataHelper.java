package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.DataHolder.emptyLogin;
import static ru.iteco.fmhandroid.ui.data.DataHolder.emptyPass;
import static ru.iteco.fmhandroid.ui.data.DataHolder.invalidLogin;
import static ru.iteco.fmhandroid.ui.data.DataHolder.invalidPass;
import static ru.iteco.fmhandroid.ui.data.DataHolder.validLogin;
import static ru.iteco.fmhandroid.ui.data.DataHolder.validPass;

import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class DataHelper {
    public DataHelper() {
    }

    public static String[] getAllCategories() {
        return Rand.CATEGORIES;
    }

    public static class User {
        private final String login;
        private final String pass;

        public User(String login, String pass) {
            this.login = login;
            this.pass = pass;
        }

        public String getLogin() {
            return login;
        }

        public String getPass() {
            return pass;
        }
    }

    public static User authInfo() {
        return new User(validLogin, validPass);
    }

    public static User invalidAuthData() {
        return new User(invalidLogin, invalidPass);
    }

    public static User invalidLoginData() {
        return new User(invalidLogin, validPass);
    }

    public static User invalidPassData() {
        return new User(validLogin, invalidPass);
    }

    public static User emptyLogin() {
        return new User(emptyLogin, validPass);
    }

    public static User emptyPassword() {
        return new User(validLogin, emptyPass);
    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> childAtPosition(
            Matcher<View> parentMatcher, final Matcher<View> parentMatcherToMatch, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcherToMatch.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcherToMatch.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public static ViewAction nestedScrollTo() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(
                        isDescendantOfA(isAssignableFrom(NestedScrollView.class)),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE));
            }

            @Override
            public String getDescription() {
                return "Scroll to view in NestedScrollView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                NestedScrollView nestedScrollView = findParentOfClass(view, NestedScrollView.class);
                if (nestedScrollView != null) {
                    nestedScrollView.scrollTo(0, view.getTop());
                } else {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(new Exception("Unable to find NestedScrollView parent."))
                            .build();
                }
                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    public static String getText(ViewInteraction matcher) {
        final String[] text = new String[1];
        ViewAction viewAction = new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Get text from TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                text[0] = ((TextView) view).getText().toString();
            }
        };
        matcher.perform(viewAction);
        return text[0];
    }

    private static <T> T findParentOfClass(View view, Class<T> parentClass) {
        ViewParent parent = view.getParent();
        while (parent != null && !parentClass.isInstance(parent)) {
            parent = parent.getParent();
        }
        return parentClass.cast(parent);
    }

    public static class Rand {
        private static final Random rand = new Random();
        private static final String[] CATEGORIES = {
                "Объявление",
                "День рождения",
                "Зарплата",
                "Профсоюз",
                "Праздник",
                "Массаж",
                "Благодарность",
                "Нужна помощь"
        };

        @SafeVarargs
        public static int random(@NonNull int... items) {
            return items[rand.nextInt(items.length)];
        }

        public static String randomCategory() {
            return CATEGORIES[rand.nextInt(CATEGORIES.length)];
        }
    }

    public static class ToastMatcher extends TypeSafeMatcher<Root> {
        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if (type == WindowManager.LayoutParams.TYPE_TOAST) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                return windowToken == appToken;
            }
            return false;
        }
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static String getCurrentTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return timeFormat.format(new Date());
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return createWaitAction(withId(viewId), millis, "view with id <" + viewId + ">");
    }

    public static ViewAction waitForElement(final Matcher<View> matcher, final long millis) {
        return createWaitAction(matcher, millis, "view with attribute <" + matcher + ">");
    }

    private static ViewAction createWaitAction(final Matcher<View> matcher, final long millis, final String description) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + description + " during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (matcher.matches(child)) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static void elementWaiting(Matcher<View> matcher, int millis) {
        onView(isRoot()).perform(waitForElement(matcher, millis));
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}


