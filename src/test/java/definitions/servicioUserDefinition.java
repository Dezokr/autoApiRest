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

    @Given("que no existe un usuario registrado")
    public void queNoExisteUnUsuarioRegistrado() {

    }

    @When("registrar datos de usuario")
    public void registrarDatosDeUsuario(DataTable dt) {
        List<Map<String,String>> data = dt.asMaps(String.class,String.class);
        for (int i=0; i<data.size();i++){
            rUser.createUser(data.get(i).get("nombre"),data.get(i).get("puesto"));
            validarCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }

    @And("mostrar datos del registro")
    public void mostrarDatosDelRegistro() {
        mostrarElListadoDeUsuarios();
    }


    @Given("que no existe usuario")
    public void queNoExisteUsuario() {
    }
    @When("registrar usuario")
    public void registrarUsuario(DataTable dt) {
        List<Map<String,String>> data = dt.asMaps(String.class,String.class);
        for (int i=0; i<data.size();i++){
            rUser.registrarUsuario(data.get(i).get("correo"),data.get(i).get("clave"));
            mostrarElListadoDeUsuarios();
        }
    }

    @Then("mostrar datos registrados")
    public void mostrarDatosRegistrados() {
        mostrarElListadoDeUsuarios();
    }

    @Given("el usuario esta registrado")
    public void elUsuarioEstaRegistrado() {

    }

    @When("actualizar datos de usuario")
    public void actualizarDatosDeUsuario(DataTable dt) {
        List<Map<String,String>> data = dt.asMaps(String.class,String.class);
        for (int i=0; i<data.size();i++){
            rUser.updateUser(data.get(i).get("id"),data.get(i).get("nombre"),data.get(i).get("puesto"));
            validarCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }

    }

    @When("actualizar datos de usuario usando patch")
    public void actualizarDatosDeUsuarioUsandoPatch(DataTable dt) {
        List<Map<String,String>> data = dt.asMaps(String.class,String.class);
        for (int i=0; i<data.size();i++){
            rUser.patchUser(data.get(i).get("id"),data.get(i).get("nombre"),data.get(i).get("puesto"));
            validarCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }
}


