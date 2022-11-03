import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReqRestServices {

    @Test
    public void helloTestingWorld(){
        System.out.printf("Hello Testing Luis Hernan");
    }

    @BeforeEach
    public void setup(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";

        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter()); // agregando los loogs
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }

    @Test
    public void LoginTest(){
               given()
                //.log().all()
                //.contentType(ContentType.JSON)
                       .body("{\n" +
                        ("  \"email\":\"eve.holt@reqres.in\",\n"+
                        "  \"password\":\"cityslicka\"\n"+
                        "}"))
                 .post("login")
                 .then().statusCode(HttpStatus.SC_OK)
                 .body("token",notNullValue());
                //.log().all()
                //.extract().asString();

     //System.out.println("la respuesta es :\n"+response);
    }

    @Test
    public void GetListUser(){
        //RestAssured
                when()
                .get("https://reqres.in/api/user?page=2")
                //.then().log().all();
                    .then()
                .body("page",equalTo(2));
    }
   @Test
    public void deleteUser(){
       when().delete("https://reqres.in/api/users/2");
               //.then()
               //.log().all();

   }
   @Test
    public void UpdateUser(){
       given()
               //.log().all()
               //.contentType(ContentType.JSON)
               .body("{\n" +
                       "  \"email\":\"eve.holt@reqres.in\",\n"+
                       "  \"password\":\"cityslicka\"\n"+
                       "}")
               .put("https://reqres.in/api/users/7");
               //.then()
               //.log().all()
               //.extract().asString();
   }

   @Test
    public void PatchUser(){
      String nameUpdate= given().
               when()
               .body("{\n" +
                       "    \"name\": \"morpheus\",\n" +
                       "    \"job\": \"zion resident\"\n" +
                       "}")
               .patch("user/2").then().statusCode(HttpStatus.SC_OK)
               .extract().jsonPath().getString("name");

       assertThat(nameUpdate,equalTo("morpheus"));
   }
}
