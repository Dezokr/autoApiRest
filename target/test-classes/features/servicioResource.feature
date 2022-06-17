#languaje en

  Feature: Pruebas al servicio Resource
    Scenario: Listar los recursos
      Given listar recursos
      When mostrar lista de recursos
      And validar codigo de respuesta resource "200"
      Then validar numero de registros resource

      Scenario: Listar un solo recurso
        Given listar recurso con id "2"
        When mostrar informacion de recurso
        And validar codigo de respuesta resource "200"
        Then validar informacion de consulta
          | name         | year | color   |
          | fuchsia rose | 2001 | #C74375 |

      Scenario: Recurso no encontrado
        Given listar recurso con id "23"
        Then validar codigo de respuesta resource "404"

