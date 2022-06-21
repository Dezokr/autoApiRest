package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class payloadRegister {
    @JsonProperty(value="email") private String correo;
    @JsonProperty(value="password") private String clave;

    public payloadRegister(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }
}
