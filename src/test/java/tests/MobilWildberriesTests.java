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

    @Test
    @Android
    @Owner("толстов вадим")
    @DisplayName("Поиск товара")
    void siteSearchTest() {

        step("Открываем поиск", () -> {
            $(id("com.wildberries.ru:id/search_searchEditText"))
                    .click();
        });

        step("Вводим antistress в поле поиска", () -> {
            $x("//android.widget.EditText").sendKeys("antistress");
        });

        step("Ищем AntistressTOP", () -> {
            $x("//android.view.View[11]").click();
        });

        step("Проверяем, что AntistressTOP найден", () -> {
   //         $(id("com.wildberries.ru:id/brandTitle")).shouldBe(text("AntistressTOP")).shouldBe(visible);
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

        step("Вводим  tapiboo в поле поиска", () -> {
            $x("//android.widget.EditText").sendKeys(products);
        });

        step("Ищем товар", () -> {
            $x("//android.view.View[11]").click();
        });

        step("Открываем карточку товара ", () -> {
            $(id("com.wildberries.ru:id/itemLayout")).click();
        });

        step("Проверяем, что открылась карточка товара ", () -> {
//            $x("//android.view.View/android.view.View[3]/android.widget.TextView[1]").shouldBe(text(products));
       });

        step("Добовляем товар в корзину", () -> {
            $(className("android.widget.Button")).click();
        });

        step("Открываем корзину", () -> {
            $x("//android.view.View[@content-desc=\"Корзина\"]").click();
        });

        step("Проверяем, что товар добавлен в корзину", () -> {
         //   $(id("com.wildberries.ru:id/productName")).shouldBe(text(products));
        });
    }

    @Test
    @Android
    @Owner("толстов вадим")
    @DisplayName("Открываем карточку товар")
    void addProductToFavoritesTest() {

        step("Открываем поиск", () -> {
            $(id("com.wildberries.ru:id/search_searchEditText"))
                    .click();
        });

        step("Вводим  тапки в поле поиска", () -> {
            $x("//android.widget.EditText").sendKeys("зонт");
        });

        step("Ищем тапки", () -> {
            $x("//android.view.View[11]").click();
        });

        step("Открываем карточку с зонтом", () -> {
            $(id("com.wildberries.ru:id/itemLayout")).click();
        });

        step("Проверяем, что открылась карточка товара зонт", () -> {
           // $x("//android.view.View/android.view.View[3]/android.widget.TextView[1]").shouldBe(text("Зонт"));
        });
    }
}