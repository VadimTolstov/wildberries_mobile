package tests;

import helpers.Android;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;

@Owner("толстов вадим")
@Epic("Android UI tests wildberries")
public class MobilWildberriesTests extends TestBase {


    @BeforeEach
    @Owner("толстов вадим")
    @Disabled("Открываем главную страницу")
    public void mainPage() {
        step("Выбераем страну Россия", () -> {
            $x("//android.widget.TextView")
                    .click();
        });

        step("Пропускаем описание", () -> {
            $(className("android.widget.Button")).click();
        });

        step("Проверяем, что открылась главная страница", () -> {
            $(id("com.wildberries.ru:id/search_searchEditText"))
                    .shouldHave(text("Поиск"));
        });
    }

    @ValueSource(strings = {
            "Зонт", "Кружка"
    })

    @ParameterizedTest(name = "Добовляем товар {0} в корзину")
    @Android
    @Owner("толстов вадим")
    @DisplayName("-")
    void openProductCardTest(String products) {

        step("Открываем поиск", () -> {
            $(id("com.wildberries.ru:id/search_searchEditText"))
                    .click();
        });

        step("Вводим  товар в поле поиска", () -> {
            $x("//android.widget.EditText").sendKeys(products);
        });

        step("Ищем товар", () -> {
            $x("//android.view.View[11]").shouldHave(visible).click();
        });

        step("Открываем карточку товара ", () -> {
            $(id("com.wildberries.ru:id/imageItemContainer")).shouldHave(visible).click();
        });

        step("Добовляем товар в корзину", () -> {
            $(className("android.widget.Button")).shouldHave(visible).click();
        });

        step("Открываем корзину", () -> {
            $x("//android.view.View[@content-desc=\"Корзина\"]").click();
        });

        step("Проверяем, что товар добавлен в корзину", () -> {
            $(id("com.wildberries.ru:id/productName")).shouldBe(text(products));
        });
    }

    @Test
    @Android
    @Owner("толстов вадим")
    @DisplayName("Проверяем версию приложения")
    void addProductToFavoritesTest() {

        step("Открываем профиль", () -> {
            $x("//android.view.View[@content-desc=\"Профиль\"]")
                    .click();
        });

        step("Открываем страницу О приложении", () -> {
            $x("//android.view.View/android.view.View/android.widget.TextView[3]").click();
        });

        step("Проверяем, что открылась страница О приложении ", () -> {
            $x("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/" +
               "android.view.View[6]/android.widget.TextView").shouldHave(text("О приложении"));
        });
    }
    @Test
    @Android
    @Owner("толстов вадим")
    @DisplayName("Смена валюты")
    void siteSearchTest() {

        step("Открываем модальное окно выбара валюты", () -> {
            $(id("com.wildberries.ru:id/savedCurrencyNameView"))
                    .click();
        });

        step("Выбераем Белорусский рубль", () -> {
            $x("//android.view.View[@content-desc=\"Белорусский рубль\"]").click();
        });


        step("Проверяем, что выбран Белорусский рубль", () -> {
            $(id("com.wildberries.ru:id/savedCurrencyNameView")).shouldHave(visible);
        });
    }
}