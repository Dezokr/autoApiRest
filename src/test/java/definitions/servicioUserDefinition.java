package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.requestUser;

import java.util.List;
import java.util.Map;

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

    @Then("validar numero de registros")
    public void validarNumeroDeRegistros() {
        ResponseBody body = requestUser.responseUser;
        JsonPath json = new JsonPath(body.asString());
        List<String> listado = json.with(body.asString()).get("data");
        int cantidad = json.getInt("per_page");
        Assert.assertEquals(cantidad,listado.size());
    }

    @Given("listar usuarios con id {string}")
    public void listarUsuariosConId(String id) {
        rUser.getSingleUser(id);
    }

    @When("mostrar informacion de usuario")
    public void mostrarInformacionDeUsuario() {
        mostrarElListadoDeUsuarios();
    }

    @Then("validar información de la consulta")
    public void validarInformaciónDeLaConsulta(DataTable user) {
        ResponseBody body = requestUser.responseUser;
        JsonPath json = new JsonPath(body.asString()).setRootPath("data");
        List<Map<String,String>> data = user.asMaps(String.class,String.class);
        for (int i=0; i<data.size();i++){
            Assert.assertEquals(data.get(i).get("email"),json.getString("email"));
            Assert.assertEquals(data.get(i).get("nombre"),json.getString("first_name"));
            Assert.assertEquals(data.get(i).get("apellido"),json.getString("last_name"));
        }

    }
}
