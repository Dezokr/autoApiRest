#language: en

  Feature: Pruebas al servicio usuarios
    Scenario: Listar todos los usuarios
      Given listar usuarios
      When mostrar el listado de usuarios
      And validar codigo de respuesta "200"
