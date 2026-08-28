package api;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import objects.User;
import utils.ConfigUtils;
import utils.EndPoints;
import utils.UserUtils;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegisterAPI {

    private List<Cookie> restAssuredCookies;
    private String accessToken;
    private String userId;
    private String firstName;

    public RegisterAPI register() {

        User user = UserUtils.generateRandomUser();

        Response response = given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType("application/json")
                .body(user)
                .log().all()
                .when()
                .post(EndPoints.API_REGISTER_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract()
                .response();

        restAssuredCookies = response.detailedCookies().asList();
        System.out.println("========== REST Assured Cookies ==========");

        for (Cookie cookie : restAssuredCookies) {
            System.out.println(
                    cookie.getName() + " = " +
                            cookie.getValue() +
                            " | Domain = " + cookie.getDomain() +
                            " | Path = " + cookie.getPath()
            );
        }

        accessToken = response.path("access_token");
        userId = response.path("userID");
        firstName = response.path("firstName");

        return this;
    }

    public List<Cookie> getRestAssuredCookies() {
        return restAssuredCookies;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }
}