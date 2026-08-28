package api;

import io.restassured.response.Response;
import objects.Task;
import utils.ConfigUtils;
import utils.EndPoints;

import static io.restassured.RestAssured.given;

public class TasksAPI {

    public void addTask(String token, String taskName) {

        Task task = new Task(taskName, false);

        Response response = given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType("application/json")
                .body(task)
                .auth().oauth2(token)
                .when()
                .post(EndPoints.API_TASK_ENDPOINT)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() != 201) {
            throw new RuntimeException(
                    "Failed : HTTP error code : " + response.getStatusCode()
            );
        }
    }
}