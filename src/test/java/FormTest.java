import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @BeforeAll
    static void browserConfigurations(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void testForm() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Practice Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("test1");
        $("#lastName").setValue("test1");
        $("#userEmail").setValue("test1@local.local");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("8963598710");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Social Studies").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("test_summer_copy.jpg"); // только для элементов с type = "file"
        $("#currentAddress").setValue("test_address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Gurgaon")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(Condition.text("Student Name test1 test1"));
        $(".table-responsive").shouldHave(Condition.text("Student Email test1@local.local"));
        $(".table-responsive").shouldHave(Condition.text("Gender Female"));
        $(".table-responsive").shouldHave(Condition.text("Mobile 8963598710"));
        $(".table-responsive").shouldHave(Condition.text("Date of Birth 30 July,1990"));
        $(".table-responsive").shouldHave(Condition.text("Subjects Social Studies"));
        $(".table-responsive").shouldHave(Condition.text("Hobbies Music"));
        $(".table-responsive").shouldHave(Condition.text("Picture test_summer_copy.jpg"));
        $(".table-responsive").shouldHave(Condition.text("Address test_address"));
        $(".table-responsive").shouldHave(Condition.text("State and City NCR Gurgaon"));
    }
}
