package ru.iteco.fmhandroid.ui.data;

import static ru.iteco.fmhandroid.ui.data.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.DataHelper.getCurrentTime;


import io.bloco.faker.Faker;

public class DataHolder {

    public static final String validLogin = "login2";
    public static final String validPass = "password2";
    public static final String emptyLogin = "";
    public static final String emptyPass = "";

    static Faker faker = new Faker();
    public static final String invalidLogin = faker.name.firstName() + faker.number.between(1, 10);
    public static final String invalidPass = faker.internet.password();


    public String categoryEmpty = "";
    public String categoryNonExistent = "Отпуск";
    public String dateOfPublic = getCurrentDate();
    public String timeOfPublic = getCurrentTime();
    public String dateWithoutNews = "01.01.2000";
    public String descriptionOnCyr = "Содержание новости";
    public String descriptionOnCyrDelete = "Новость для удаления";
    public String titleCyr = "Заголовок новости";
    public String titleCyrDelete = "На удаление";
    public String descriptionEmptyText = "";
    public String editTitle = "Отредактированный заголовок";
    public String editDescription = "Отредактированное содержание новости";
    public String newTitle = "Новый заголовок новости";
    public String newDescription = "Новое содержание новости";
    public String quoteText = "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. " +
            "Но всегда он добрый, любящий и помогающий.";


}

