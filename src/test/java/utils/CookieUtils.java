package utils;

import io.restassured.http.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    private CookieUtils(){}

    public static List<org.openqa.selenium.Cookie> convertRestAssuredCookiesToSeleniumCookies(
            List<Cookie> restAssuredCookies){

        List<org.openqa.selenium.Cookie> seleniumCookies = new ArrayList<>();

        for(Cookie cookie : restAssuredCookies){

            org.openqa.selenium.Cookie seleniumCookie =
                    new org.openqa.selenium.Cookie.Builder(
                            cookie.getName(),
                            cookie.getValue())
                            .domain(cookie.getDomain())
                            .path(cookie.getPath())
                            .isSecure(cookie.isSecured())
                            .build();

            seleniumCookies.add(seleniumCookie);

        }

        return seleniumCookies;

    }

}