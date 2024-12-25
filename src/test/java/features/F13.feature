Feature: Gestionar direcciones de envío
  Para recibir mis pedidos en la dirección correcta
  Como usuario de la tienda
  Quiero poder agregar, editar y eliminar direcciones de envío en mi cuenta

  # Escenario para intentar crear una nueva dirección sin rellenar ningún campo
  Scenario: Intentar crear una nueva dirección sin rellenar ningún campo
    Given El usuario está logeado en su cuenta 3
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Address Book" 3
    And El usuario intenta agregar una nueva dirección sin completar ningún campo
    Then El sistema debería mostrar un error indicando que todos los campos son obligatorios

  # Escenario para añadir una nueva dirección de envío
  Scenario: Añadir una nueva dirección de envío
    Given El usuario está logeado en su cuenta 3
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Address Book" 3
    And El usuario agrega una nueva dirección con:
      | input-firstname        | Javier             |
      | input-lastname         | David              |
      | input-company          | MiEmpresa          |
      | input-address-1        | Calle Falsa 123    |
      | input-address-2        | Piso 3, Puerta A   |
      | input-city             | Madrid             |
      | input-postcode         | 28000              |
      | input-country          | Spain              |
      | input-zone             | Madrid             |
    Then La dirección debería ser guardada y aparecer en la lista de direcciones del usuario

  # Escenario para intentar editar una dirección borrando la información de todos los campos
  Scenario: Intentar editar una dirección borrando la información de todos los campos
    Given El usuario está logeado en su cuenta 3
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Address Book" 3
    And El usuario intenta editar una dirección borrando la información de todos los campos
    Then El sistema debería mostrar un error indicando que todos los campos son obligatorios
    
  # Escenario para editar una dirección existente
  Scenario: Editar una dirección existente
    Given El usuario está logeado en su cuenta 3
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Address Book" 3
    And El usuario edita la dirección a:
      | input-firstname        | David              |
      | input-lastname         | Javier             |
      | input-company          | MiNuevaEmpresa     |
      | input-address-1        | Calle Verdadera 456|
      | input-address-2        | Piso 4, Puerta B   |
      | input-city             | Barcelona          |
      | input-postcode         | 28001              |
      | input-country          | Spain              |
      | input-zone             | Barcelona          |
    Then La dirección debería ser actualizada correctamente en la cuenta del usuario

  # Escenario para eliminar una dirección de envío
  Scenario: Eliminar una dirección de envío
    Given El usuario está logeado en su cuenta 3
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Address Book" 3
    And El usuario elimina la dirección
    Then La dirección debería ser eliminada y ya no aparecer en la lista de direcciones

  # Escenario para intentar borrar una dirección cuando solo existe una
  Scenario: Intentar borrar una dirección cuando solo existe una
    Given El usuario está logeado en su cuenta 3
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Address Book" 3
    And El usuario intenta eliminar la única dirección que tiene
    Then El sistema debería mostrar un error indicando que no se puede eliminar la última dirección