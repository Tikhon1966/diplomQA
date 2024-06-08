package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.companyInfoLabel;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.privacyPolicyLabel;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.privacyPolicyValue;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.returnButton;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.termsOfUseLabel;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.termsOfUseValue;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.trademarkImage;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.versionTitle;
import static ru.iteco.fmhandroid.ui.screenElements.AboutElements.versionValue;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.screenElements.AboutElements;

public class AboutSteps {

    public void verifyAboutScreenElements() {
        Allure.step("Проверка отображения всех элементов на экране 'О приложении'");
        elementWaiting(AboutElements.getCompanyInfoMatcher(), 10000);
        trademarkImage.check(matches(isDisplayed()));
        versionTitle.check(matches(isDisplayed()));
        versionValue.check(matches(isDisplayed()));
        privacyPolicyLabel.check(matches(isDisplayed()));
        privacyPolicyValue.check(matches(isDisplayed()));
        termsOfUseLabel.check(matches(isDisplayed()));
        termsOfUseValue.check(matches(isDisplayed()));
        companyInfoLabel.check(matches(isDisplayed()));
        returnButton.check(matches(isDisplayed()));
    }

    public void verifyPrivacyPolicyLinkIsClickable() {
        Allure.step("Проверка кликабельности ссылки 'Политика конфиденциальности'");
        elementWaiting(AboutElements.getCompanyInfoMatcher(), 10000);
        privacyPolicyValue.check(matches(isClickable()));
    }

    public void verifyTermsOfUseLinkIsClickable() {
        Allure.step("Проверка кликабельности ссылки 'Условия использования'");
        termsOfUseValue.check(matches(isClickable()));
    }

    public void clickReturnButton() {
        Allure.step("Нажатие кнопки 'Назад'");
        returnButton.perform(click());
    }
}
