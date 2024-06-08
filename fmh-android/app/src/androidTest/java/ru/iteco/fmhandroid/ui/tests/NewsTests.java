package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.action.ViewActions.swipeDown;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.iteco.fmhandroid.ui.data.DataHelper.Rand.randomCategory;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.DataHolder;
import ru.iteco.fmhandroid.ui.screenElements.NewsElements;
import ru.iteco.fmhandroid.ui.screenElements.PanelElements;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.NewsCreationSteps;
import ru.iteco.fmhandroid.ui.steps.NewsEditSteps;
import ru.iteco.fmhandroid.ui.steps.NewsFilterSteps;
import ru.iteco.fmhandroid.ui.steps.GeneralSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.PanelSteps;
import ru.iteco.fmhandroid.ui.steps.SplashSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

    AuthSteps authSteps = new AuthSteps();
    NewsSteps newsSteps = new NewsSteps();
    MainSteps mainSteps = new MainSteps();
    PanelSteps panelSteps = new PanelSteps();
    NewsFilterSteps filterScreen = new NewsFilterSteps();
    DataHolder dataHolder = new DataHolder();
    NewsCreationSteps newsCreationSteps = new NewsCreationSteps();
    GeneralSteps generalSteps = new GeneralSteps();
    NewsEditSteps newsEditSteps = new NewsEditSteps();
    SplashSteps splashSteps = new SplashSteps();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        splashSteps.appDownload();
        try {
            mainSteps.waitForMainScreenLoad();
        } catch (Exception e) {
            authSteps.loginWithValidCredentials(DataHelper.authInfo());
            authSteps.clickSignInButton();
        } finally {
            mainSteps.waitForMainScreenLoad();
            mainSteps.clickAllNewsButton();
        }
    }

    @Test
    @DisplayName("Создание новости на кириллице")
    @Description("Заполнение полей и создание новости с данными на кириллице")
    public void shouldCreateNewsWithCyrillicData() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        newsCreationSteps.createNews(randomCategory(), dataHolder.titleCyr, dataHolder.dateOfPublic,
                dataHolder.timeOfPublic, dataHolder.descriptionOnCyr);
        generalSteps.clickSaveButton();
        mainSteps.navigateToNews();
        newsSteps.loadNewsList();
        panelSteps.checkCreateNews(0, dataHolder.titleCyr, dataHolder.descriptionOnCyr);
        NewsElements.allNewsBlock.perform(swipeDown());
        newsSteps.expandNewsItem(0);
        String createdDescription = newsSteps.getCreatedNewsDescription(0);
        assertEquals(dataHolder.descriptionOnCyr, createdDescription);
    }


    @Test
    @DisplayName("Создание и удаление новости")
    @Description("Создание новости с данными на кириллице, затем удаление этой новости")
    public void shouldCreateAndDeleteNewsWithCyrillicData() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        newsCreationSteps.createNews(randomCategory(), dataHolder.titleCyrDelete, dataHolder.dateOfPublic,
                dataHolder.timeOfPublic, dataHolder.descriptionOnCyrDelete);
        generalSteps.clickSaveButton();
        mainSteps.navigateToNews();
        newsSteps.loadNewsList();
        NewsElements.allNewsBlock.perform(swipeDown());
        newsSteps.expandNewsItem(0);
        String createdDescription = newsSteps.getCreatedNewsDescription(0);
        assertEquals(dataHolder.descriptionOnCyrDelete, createdDescription);
        newsSteps.loadNewsList();
        NewsElements.allNewsBlock.perform(swipeDown());
        newsSteps.clickEditNewsButton();
        panelSteps.clickDeleteNews(0);
        generalSteps.clickOkButton();
        mainSteps.navigateToNews();
        newsSteps.loadNewsList();
        NewsElements.allNewsBlock.perform(swipeDown());
        newsSteps.expandNewsItem(0);
        boolean isNewsDeleted = newsSteps.isNewsDeleted(dataHolder.titleCyrDelete, dataHolder.descriptionOnCyrDelete);
        assertTrue(isNewsDeleted);
    }

    @Test
    @DisplayName("Создание новости с пустым полем Category")
    @Description("Проверка уведомления о необходимости заполнения поля Category при создании новости")
    public void shouldVerifyCreateNewsWithEmptyCategory() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        newsCreationSteps.createNews(dataHolder.categoryEmpty, dataHolder.titleCyr, dataHolder.dateOfPublic,
                dataHolder.timeOfPublic, dataHolder.descriptionOnCyr);
        generalSteps.clickSaveButton();
        generalSteps.verifyEmptyFieldError();
    }

    @Test
    @DisplayName("Создание новости с 'невалидным' значением поля Category")
    @Description("Проверка уведомления о невозможности сохранить новость")
    public void shouldVerifyCreateNewsWithNonExistentCategory() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        newsCreationSteps.createNews(dataHolder.categoryNonExistent, dataHolder.titleCyr, dataHolder.dateOfPublic,
                dataHolder.timeOfPublic, dataHolder.descriptionOnCyr);
        generalSteps.clickSaveButton();
        generalSteps.verifyErrorSavingNewsToast();
    }

    @Test
    @DisplayName("Создание новости с пустым полем Description")
    @Description("Проверка уведомления о необходимости заполнения поля Description при создании новости")
    public void shouldVerifyCreateNewsWithEmptyDescription() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        newsCreationSteps.createNews(randomCategory(), dataHolder.titleCyr, dataHolder.dateOfPublic,
                dataHolder.timeOfPublic, dataHolder.descriptionEmptyText);
        generalSteps.clickSaveButton();
        generalSteps.verifyEmptyFieldError();
    }


    @Test
    @DisplayName("Создание новости с пустыми полями")
    @Description("Проверка уведомления о необходимости заполнения всех полей при создании новости")
    public void shouldVerifyCreateNewsWithEmptyFields() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        generalSteps.clickSaveButton();
        generalSteps.verifyEmptyFieldError();
    }

    @Test
    @DisplayName("Переход в панель управления новостями")
    @Description("Проверка перехода в панель управления новостями при нажатии на кнопку с изображением карандаша")
    public void shouldGoToControlPanel() {
        newsSteps.clickEditNewsButton();
        panelSteps.checkPanelElements();
    }

    @Test
    @DisplayName("Проверка чекбоксов в фильтре новостей")
    @Description("Проверка состояния чекбоксов активных и неактивных новостей при фильтрации")
    public void shouldVerifyCheckboxesFilter() {
        newsSteps.clickEditNewsButton();
        panelSteps.openNewsFilter();
        filterScreen.toggleActiveCheckBox();
        filterScreen.verifyActiveCheckBoxStatus(false);
        filterScreen.toggleNotActiveCheckBox();
        filterScreen.verifyNotActiveCheckBoxStatus(false);
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу")
    @Description("Проверка фильтрации новостей по статусу Active/Not Active")
    public void shouldVerifyFilterActiveNotActive() {
        newsSteps.clickEditNewsButton();
        panelSteps.openNewsFilter();
        filterScreen.toggleNotActiveCheckBox();
        filterScreen.applyFilter();
        newsSteps.loadNewsList();
        panelSteps.checkStatusActive();
        panelSteps.clickEditNews(0);
        newsEditSteps.verifyEditNewsScreenElements();
        newsEditSteps.toggleNewsStatus();
        generalSteps.clickSaveButton();
        panelSteps.clickOneNewsItem(0);
        panelSteps.openNewsFilter();
        filterScreen.toggleActiveCheckBox();
        filterScreen.applyFilter();
        newsSteps.loadNewsList();
        panelSteps.checkStatusNotActive();
    }

    @Test
    @DisplayName("Отмена фильтрации новостей через панель управления")
    @Description("Проверка отмены фильтрации новостей через панель управления")
    public void shouldVerifyCancelFilteringNews() {
        newsSteps.clickEditNewsButton();
        panelSteps.openNewsFilter();
        generalSteps.clickCancelButton();
        panelSteps.checkPanelElements();
    }

    @Test
    @DisplayName("Сортировка новостей по дате")
    @Description("Проверка изменения порядка новостей при сортировке по дате")
    public void shouldSortNews() {
        String firstNewsTitle = newsSteps.getFirstNewsTitle(0);
        newsSteps.clickSortNewsButton();
        NewsElements.allNewsBlock.perform(swipeDown());
        newsSteps.clickSortNewsButton();
        NewsElements.allNewsBlock.perform(swipeDown());
        newsSteps.loadNewsList();
        String firstNewsTitleAfterSecondSorting = newsSteps.getFirstNewsTitleAfterSecondSort(0);
        assertEquals(firstNewsTitle, firstNewsTitleAfterSecondSorting);
    }

    @Test
    @DisplayName("Фильтр по дате - нет новостей")
    @Description("Проверка, что при фильтрации по дате без новостей отображается сообщение 'There is nothing here yet'")
    public void shouldFilterNothingToShowScreen() {
        newsSteps.openNewsFilter();
        filterScreen.verifyFilterScreenElements();
        filterScreen.enterStartDate(dataHolder.dateWithoutNews);
        filterScreen.enterEndDate(dataHolder.dateWithoutNews);
        filterScreen.applyFilter();
        generalSteps.verifyNewsButterflyImage();
        generalSteps.verifyNothingToShowMessage();
    }

    @Test
    @DisplayName("Отмена фильтрации новостей")
    @Description("Проверка отмены фильтрации новостей без применения фильтра")
    public void shouldCancelingFiltering() {
        newsSteps.openNewsFilter();
        filterScreen.verifyFilterScreenElements();
        filterScreen.enterStartDate(dataHolder.dateOfPublic);
        generalSteps.clickCancelButton();
        newsSteps.verifyNewsScreenElements();
    }


    @Test
    @DisplayName("Отмена создания новости")
    @Description("Проверка отмены создания новости при подтверждении отмены")
    public void shouldVerifyCancelNewsCreate() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.verifyCreateNewsScreenElements();
        newsCreationSteps.enterPublicationDate(dataHolder.dateOfPublic);
        newsCreationSteps.enterPublicationTime(dataHolder.timeOfPublic);
        generalSteps.clickCancelButton();
        generalSteps.clickOkButton();
        panelSteps.checkPanelElements();
    }

    @Test
    @DisplayName("Отмена редактирование новости")
    @Description("Проверка отмены редактирования новости при подтверждении отмены")
    public void shouldVerifyCancelEditNews() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.createNews(randomCategory(), dataHolder.titleCyr,
                dataHolder.dateOfPublic, dataHolder.timeOfPublic, dataHolder.descriptionOnCyr);
        generalSteps.clickSaveButton();
        newsSteps.loadNewsList();
        panelSteps.clickEditNews(0);
        newsEditSteps.verifyEditNewsScreenElements();
        newsEditSteps.updateNewsTitle(dataHolder.newTitle);
        newsEditSteps.updateNewsDescription(dataHolder.newDescription);
        generalSteps.clickCancelButton();
        generalSteps.clickOkButton();
        panelSteps.checkPanelElements();
        PanelElements.newsList.perform(swipeDown());
        panelSteps.clickOneNewsItem(0);
        assertEquals(dataHolder.titleCyr, panelSteps.getEditNewsTitle(0));
    }

    @Test
    @DisplayName("Редактирование новости")
    @Description("Проверка редактирования новости с новыми данными")
    public void shouldVerifyEditNews() {
        newsSteps.clickEditNewsButton();
        panelSteps.clickCreateNewsButton();
        newsCreationSteps.createNews(randomCategory(), dataHolder.titleCyr, dataHolder.dateOfPublic,
                dataHolder.timeOfPublic, dataHolder.descriptionOnCyr);
        generalSteps.clickSaveButton();
        newsSteps.loadNewsList();
        panelSteps.clickEditNews(0);
        newsEditSteps.verifyEditNewsScreenElements();
        newsEditSteps.updateNewsTitle(dataHolder.editTitle);
        newsEditSteps.updateNewsDescription(dataHolder.editDescription);
        generalSteps.clickSaveButton();
        panelSteps.clickOneNewsItem(0);
        assertEquals(dataHolder.editTitle, panelSteps.getEditNewsTitle(0));
        assertEquals(dataHolder.editDescription, panelSteps.getEditNewsDescription(0));
    }


}
