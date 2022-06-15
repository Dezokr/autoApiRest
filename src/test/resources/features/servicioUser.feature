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