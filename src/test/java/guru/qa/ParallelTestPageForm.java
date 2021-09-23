package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import guru.qa.domian.MenuItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParallelTestPageForm {
    static final String URLMARKET = "http://automationpractice.com/";
    static final String URLSTUDENTFORM = "https://demoqa.com/automation-practice-form";
    static final String URLEDADIL = "https://edadeal.ru/moskva";

    @BeforeAll
    static void setUp() {
        Configuration.startMaximized = true;
    }

    @ValueSource(strings = {
            "T-shirts",
            "Dresses",
            "Blouse"
    })
    @ParameterizedTest(name = "FindMenu - {0}")
    void findingThingForPaige(String Goods) {
        open(URLMARKET);
        $("#search_query_top").setValue(Goods).pressEnter();
        $(".navigation_page").shouldHave(text("Search"));

    }

    static Stream<Arguments> testWithMethodSource() {
        return Stream.of(
                Arguments.of(
                        "Billi", "Gilmor", "Bill@mail.ru", "777777777"
                ),
                Arguments.of(
                        "Andy", "Serggev", "Sergio@mail.ru", "88888888"
                )
        );
    }

    @MethodSource("testWithMethodSource")
    @ParameterizedTest(name = "TestForm: {0}")
    void studentFormTest(String name, String lastname, String email, String phoneNumber) {
        open(URLSTUDENTFORM);
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastname);
        $("#userEmail").setValue(email);
        $("#userNumber").setValue(phoneNumber);
    }

    @EnumSource(value = MenuItem.class)
    @ParameterizedTest()
    void openTabsWithEnumSource(MenuItem menuItem) {
        open(URLEDADIL);
        $$(".b-header__section").find(Condition.text(menuItem.getDesc())).click();
    }

    @CsvSource(value = {
            "T-shirts, Very complex displayed name",
            "Dresses, Very complex displayed name",
            "Blouse, Very complex displayed name"
    })

    @ParameterizedTest(name = "{1}")
    void findingThingForPaigeCsv(String Goods, String testName) {
        open(URLMARKET);
        $("#search_query_top").setValue(Goods).pressEnter();
        $(".navigation_page").shouldHave(text("Search"));
    }
}

