package ru.netology.ibank.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.ibank.data.DataHelper;
import ru.netology.ibank.page.DashboardPage;
import ru.netology.ibank.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    private DashboardPage shouldOpenDashboardPage() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransfer1RURFromCard1ToCard2() {
        int amount = 1;
        DashboardPage dashboardPage = shouldOpenDashboardPage();
        int expected1 = dashboardPage.getBalanceCard1() + amount;
        int expected2 = dashboardPage.getBalanceCard2() - amount;
        val moneyTransfer = dashboardPage.card1();
        moneyTransfer.moneyTransferVisible();
        moneyTransfer.setTransferAmount(amount);
        moneyTransfer.setFrom(DataHelper.getCardNumber2());
        moneyTransfer.transfer();
        assertEquals(expected1, dashboardPage.getBalanceCard1());
        assertEquals(expected2, dashboardPage.getBalanceCard2());
    }

    @Test
    void shouldTransfer1RURFromCard2ToCard1() {
        int amount = 1;
        DashboardPage dashboardPage = shouldOpenDashboardPage();
        int expected1 = dashboardPage.getBalanceCard1() - amount;
        int expected2 = dashboardPage.getBalanceCard2() + amount;
        val moneyTransfer = dashboardPage.card2();
        moneyTransfer.moneyTransferVisible();
        moneyTransfer.setTransferAmount(amount);
        moneyTransfer.setFrom(DataHelper.getCardNumber1());
        moneyTransfer.transfer();
        assertEquals(expected1, dashboardPage.getBalanceCard1());
        assertEquals(expected2, dashboardPage.getBalanceCard2());
    }

    @Test
    void shouldNotTransferFrom2ndCardTo1stWhenAmountIs0() {
        int amount = 0;
        DashboardPage dashboardPage = shouldOpenDashboardPage();
        val moneyTransfer = dashboardPage.card1();
        moneyTransfer.moneyTransferVisible();
        moneyTransfer.setTransferAmount(amount);
        moneyTransfer.setFrom(DataHelper.getCardNumber2());
        moneyTransfer.transfer();
        moneyTransfer.error();
    }

    @Test
    void shouldNotTransferOverLimit() {
        int amount = 10001;
        DashboardPage dashboardPage = shouldOpenDashboardPage();
        val moneyTransfer = dashboardPage.card1();
        moneyTransfer.moneyTransferVisible();
        moneyTransfer.setTransferAmount(amount);
        moneyTransfer.setFrom(DataHelper.getCardNumber2());
        moneyTransfer.transfer();
        moneyTransfer.error();
    }
}