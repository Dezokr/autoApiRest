package support;

import io.restassured.response.Response;
import objects.payloadUser;

public class requestUser {
     apihelper api;
     public static Response responseUser;
     payloadUser payload;
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
        String payload = "{\"name\": \""+nombre+"\", \"job\": \""+puesto+"\"}";
        //payload = new payloadUser(nombre,puesto);
        responseUser = api.post(url,payload);
    }
}
