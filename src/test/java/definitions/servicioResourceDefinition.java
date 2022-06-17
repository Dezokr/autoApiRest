package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.requestResource;
import support.requestUser;

import java.util.List;
import java.util.Map;

public class servicioResourceDefinition {

    requestResource reqResource;

    public servicioResourceDefinition() {
        reqResource = new requestResource();
    }

    @Given("listar recursos")
    public void listarRecursos() {
        reqResource.getResources();
    }

    @When("mostrar lista de recursos")
    public void mostrarListaDeRecursos() {
        ResponseBody body = requestResource.responseResource;
        System.out.println(body.asString());
    }

    @And("validar codigo de respuesta resource {string}")
    public void validarCodigoDeRespuestaResource(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo),requestResource.responseResource.getStatusCode());
    }

    @Then("validar numero de registros resource")
    public void validarNumeroDeRegistrosResource() {
        ResponseBody body = requestResource.responseResource;
        JsonPath json = new JsonPath(body.asString());
        List<String> listado = json.with(body.asString()).get("data");
        int cantidad = json.getInt("per_page");
        Assert.assertEquals(cantidad,listado.size());
    }

    @Given("listar recurso con id {string}")
    public void listarRecursoConId(String id) {
        reqResource.getSingleResource(id);
    }

    @When("mostrar informacion de recurso")
    public void mostrarInformacionDeRecurso() {
        mostrarListaDeRecursos();
    }

    @Then("validar informacion de consulta")
    public void validarInformacionDeConsulta(DataTable resource) {
        ResponseBody body = requestResource.responseResource;
        JsonPath json = new JsonPath(body.asString()).setRootPath("data");
        List<Map<String,String>> data = resource.asMaps(String.class,String.class);
        for (int i=0; i<data.size();i++){
            Assert.assertEquals(data.get(i).get("name"),json.getString("name"));
            Assert.assertEquals(data.get(i).get("year"),json.getString("year"));
            Assert.assertEquals(data.get(i).get("color"),json.getString("color"));
        }
    }
}
