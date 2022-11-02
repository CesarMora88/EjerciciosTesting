import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class ReqRestServices {

    @Test
    public void helloTestingWorld(){
        System.out.printf("Hello Testing World");
    }

    @Test
    public void LoginTest(){
        String response = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\":\"eve.holt@reqres.in\",\n"+
                        "  \"password\":\"cityslicka\"\n"+
                        "}")
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .extract().asString();

     System.out.println("la respuesta es :\n"+response);
    }
    @Test
    public void GetListUser(){
        RestAssured
                .when()
                .get("https://reqres.in/api/user?page=2")
                .then().log().all();
    }
   @Test
    public void deleteUser(){
       RestAssured.when().delete("https://reqres.in/api/users/2")
               .then()
               .log().all();
   }
   @Test
    public void UpdateUser(){
       RestAssured
               .given()
               .log().all()
               .contentType(ContentType.JSON)
               .body("{\n" +
                       "  \"email\":\"eve.holt@reqres.in\",\n"+
                       "  \"password\":\"cityslicka\"\n"+
                       "}")
               .put("https://reqres.in/api/users/2")
               .then()
               .log().all()
               .extract().asString();
   }
}
