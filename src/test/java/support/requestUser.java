package support;

import io.restassured.response.Response;
import objects.payloadRegister;
import objects.payloadUser;

public class requestUser {
     apihelper api;
     public static Response responseUser;
     payloadUser payload;
     payloadRegister payloadR;
    public requestUser() {
        api = new apihelper();
    }

    public void getUsers(){
        String url ="https://reqres.in/api/users";
        responseUser = api.get(url);
    }
    public void getSingleUser(String id){
        String url ="https://reqres.in/api/users/"+id;
        responseUser = api.get(url);
    }
    public void createUser(String nombre, String puesto){
        String url = "https://reqres.in/api/users";
        //String payload = "{\"name\": \""+nombre+"\", \"job\": \""+puesto+"\"}";
        payload = new payloadUser(nombre,puesto);
        responseUser = api.post(url,payload);
    }

    public void registrarUsuario(String correo, String clave){
        String url = "https://reqres.in/api/register";
        payloadR = new payloadRegister(correo,clave);
        responseUser = api.post(url,payloadR);
    }

    public void updateUser(String id, String nombre, String puesto){
        String url ="https://reqres.in/api/users/"+id;
        payload = new payloadUser(nombre,puesto);
        responseUser = api.put(url,payload);
    }

    public void patchUser(String id, String nombre, String puesto){
        String url ="https://reqres.in/api/users/"+id;
        String payload = "{\"job\": \""+puesto+"\"}";
        responseUser = api.patch(url,payload);
    }
}
