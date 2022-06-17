#language: en

  Feature: Pruebas al servicio usuarios
    Scenario: Listar todos los usuarios
      Given listar usuarios
      When mostrar el listado de usuarios
      And validar codigo de respuesta "200"
      Then validar numero de registros

    Scenario: Listar usuario
      Given listar usuarios con id "5"
      When mostrar informacion de usuario
      And validar codigo de respuesta "200"
      Then validar información de la consulta
        | email                    | nombre  | apellido |
        | charles.morris@reqres.in | Charles | Morris   |
      
      Scenario: Usuario no registrado
        Given listar usuarios con id "50"
        Then validar codigo de respuesta "404"

      Scenario: Crear usuario
        Given que no existe un usuario registrado
        When  registrar datos de usuario
          | nombre | puesto | codigo |
          | Renzo  | QA     | 201    |
          | Paolo  | TAE    | 201    |
          | Mario  | PO     | 201    |
        Then validar codigo de respuesta "201"
        And mostrar datos del registro
        