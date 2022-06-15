package definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.requestUser;

public class servicioUserDefinition {
    requestUser rUser;

    public servicioUserDefinition() {
        rUser = new requestUser();
    }

    @Given("listar usuarios")
    public void listarUsuarios() {
        rUser.getUsers();
    }

    @When("mostrar el listado de usuarios")
    public void mostrarElListadoDeUsuarios() {
        ResponseBody body = requestUser.responseUser;
        System.out.println(body.asString());
    }

    @And("validar codigo de respuesta {string}")
    public void validarCodigoDeRespuesta(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo),requestUser.responseUser.getStatusCode());
    }
}
