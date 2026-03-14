package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;

public class LoginTest {

    public static void main(String[] args) {
//        System.setProperty(
//                "webdriver.chrome.driver",
//                "C:\\Users\\Dell\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
//        );

        HomePage homePage= new HomePage(CHROME);
        String userName = homePage.goToLoginPage().doLoginWith("focaweh930@mekuron.com","password").getUserName( );
        System.out.println(userName);

    }
}
