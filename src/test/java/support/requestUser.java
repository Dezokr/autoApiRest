package support;

import io.restassured.response.Response;

public class requestUser {
     apihelper api;
     public static Response responseUser;

    public requestUser() {
        api = new apihelper();
    }

    public void getUsers(){
        String url ="https://reqres.in/api/users";
        responseUser = api.get(url);

    }
}