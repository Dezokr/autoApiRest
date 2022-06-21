#language: en
  @AlLServices
  Feature: Pruebas al servicio usuarios

    @GET @REGRESSION
    Scenario: Listar todos los usuarios
      Given listar usuarios
      When mostrar el listado de usuarios
      And validar codigo de respuesta "200"
      Then validar numero de registros

    @GET
    Scenario: Listar usuario
      Given listar usuarios con id "5"
      When mostrar informacion de usuario
      And validar codigo de respuesta "200"
      Then validar información de la consulta
        | email                    | nombre  | apellido |
        | charles.morris@reqres.in | Charles | Morris   |

    @GET
    Scenario: Usuario no registrado
      Given listar usuarios con id "50"
      Then validar codigo de respuesta "404"

    @POST @REGRESSION
    Scenario: Crear usuario
      Given que no existe un usuario registrado
      When  registrar datos de usuario
        | nombre | puesto | codigo |
        | Renzo  | QA     | 201    |
        | Paolo  | TAE    | 201    |
        | Mario  | PO     | 201    |
      Then validar codigo de respuesta "201"
      And mostrar datos del registro

    @POST
    Scenario: Registro de datos exitoso
      Given que no existe usuario
      When registrar usuario
        | correo         | clave  |
        | eve.holt@reqres.in | pistol |
      Then mostrar datos registrados

    @PUT @REGRESSION
    Scenario: Actualizar usuario
     Given el usuario esta registrado
     When actualizar datos de usuario
       | id | nombre  | puesto | codigo |
       | 2  | Renzo   | QA     | 200    |
       | 8  | Mario   | TAE    | 200    |
       | 9  | Rodolfo | PO     | 200    |
     Then validar codigo de respuesta "200"
     And mostrar datos del registro

    @PATCH
    Scenario: Actualizar usuario usando patch
      Given el usuario esta registrado
      When actualizar datos de usuario usando patch
        | id | nombre  | puesto | codigo |
        | 2  | Renzo   | BO     | 200    |
        | 8  | Mario   | DEV    | 200    |
        | 9  | Rodolfo | SM     | 200    |
      Then validar codigo de respuesta "200"
      And mostrar datos del registro