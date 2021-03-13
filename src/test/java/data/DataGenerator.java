package data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.*;

public class DataGenerator {

    static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void request(DataRegistration dataRegistration) {
        given()
                .spec(requestSpec)
                .body(dataRegistration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static DataRegistration activeUser() {
        String status = "active";
        DataRegistration user = new DataRegistration(faker.name().firstName(), faker.internet().password(), status);
        request(user);
        return user;
    }

    public static DataRegistration blockedUser() {
        String status = "blocked";
        DataRegistration user = new DataRegistration(faker.name().firstName(), faker.internet().password(), status);
        request(user);
        return user;
    }

    public static DataRegistration invalidLogin() {
        String login = "nikita";
        String status = "active";
        DataRegistration user = new DataRegistration(login, faker.internet().password(), status);
        request(user);
        return new DataRegistration("login",faker.internet().password(),status);
    }

    public static DataRegistration invalidPassword() {
        String password = "invalid password";
        String status = "active";
        DataRegistration user = new DataRegistration(faker.name().firstName(), password, status);
        request(user);
        return new DataRegistration(faker.name().firstName(),"password",status);
    }
}
