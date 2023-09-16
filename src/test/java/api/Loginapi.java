package api;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Utilites.PropertiesHandler;

public class Loginapi {

    PropertiesHandler Prop =new PropertiesHandler();
    private String Token;

    @Test
    @Description("Login with supported credentials usinf end point and param1 from json file then makking some assertions then " +
            "extract the body to take the token use it in nesxt end point ")
    public void login() throws IOException, ParseException {

        Response response =

        given().pathParam("param",Prop.json("param1")).headers("Content-Type", "application/json").body(Prop.json("Body"))
                .when().post(Prop.json("url"))
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("token[0]",not(isEmptyOrNullString()))
                .extract().response();

        Token=response.path("token");
    }

    @Test
    @Description("From the token that been extracted from first end point i will get the info of my account ")
    public void Whoiam() throws IOException, ParseException {
        given().pathParam("param",Prop.json("param2")).headers("Content-Type", "application/json","token",Token)
                .when().get(Prop.json("url"))
                .then()
                .assertThat().statusCode(200);

    }




}
