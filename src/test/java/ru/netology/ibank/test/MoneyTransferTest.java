package ru.netology.ibank.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.ibank.Data.DataHelper;
import ru.netology.ibank.Page.LoginPage;
import ru.netology.ibank.Page.VerificationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @Test
    void shouldTransfer1RURBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[1]/div/button").click();
        $("[data-test-id=amount] input").setValue("1");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[1]").shouldHave(text("10001"));
        $x("//div/ul/li[2]/div/button").click();
        $("[data-test-id=amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue("1");
        $("[data-test-id=from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[2]").shouldHave(text("10000"));
    }

    @Test
    void shouldTransfer2RURBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[1]/div/button").click();
        $("[data-test-id=amount] input").setValue("2");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[1]").shouldHave(text("10002"));
        $x("//div/ul/li[2]/div/button").click();
        $("[data-test-id=amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue("2");
        $("[data-test-id=from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[2]").shouldHave(text("10000"));
    }

    @Test
    void shouldNotTransferFrom1stCardTo2ndWhenAmountIs0() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[1]/div/button").click();
        $("[data-test-id=amount] input").setValue("0");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[1]").shouldHave(text("10000"));
    }

    @Test
    void shouldNotTransferFrom2ndCardTo1stWhenAmountIs0() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[2]/div/button").click();
        $("[data-test-id=amount] input").setValue("0");
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[2]").shouldHave(text("10000"));
    }

    @Test
    void shouldTransfer9999RURBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[1]/div/button").click();
        $("[data-test-id=amount] input").setValue("9999");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[1]").shouldHave(text("19999"));
        $x("//div/ul/li[2]/div/button").click();
        $("[data-test-id=amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue("9999");
        $("[data-test-id=from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[2]").shouldHave(text("10000"));
    }
    @Test
    void shouldTransfer10000RURBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[1]/div/button").click();
        $("[data-test-id=amount] input").setValue("10000");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[1]").shouldHave(text("20000"));
        $x("//div/ul/li[2]/div/button").click();
        $("[data-test-id=amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue("10000");
        $("[data-test-id=from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[2]").shouldHave(text("10000"));
    }
    @Test
    void shouldNotTransferOverLimit() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        $x("//div/ul/li[1]/div/button").click();
        $("[data-test-id=amount] input").setValue("10001");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[1]").shouldHave(text("20000"));
        $x("//div/ul/li[2]/div/button").click();
        $("[data-test-id=amount] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=amount] input").setValue("10000");
        $("[data-test-id=from] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $x("//*[@id=\"root\"]/div/ul/li[2]").shouldHave(text("10000"));
    }
}
