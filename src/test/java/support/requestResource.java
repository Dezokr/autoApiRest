package support;

import io.restassured.response.Response;

public class requestResource {

    apihelper api;
    public static Response responseResource;

    public requestResource() {
        api = new apihelper();
    }

    public void getResources(){
        String url ="https://reqres.in/api/unknown";
        responseResource = api.get(url);
    }
    public void getSingleResource(String id){
        String url ="https://reqres.in/api/unknown/"+id;
        responseResource = api.get(url);
    }
}
