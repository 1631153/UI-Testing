Feature: Gestionar direcciones de envío
  Para recibir mis pedidos en la dirección correcta
  Como usuario de la tienda
  Quiero poder agregar, editar y eliminar direcciones de envío en mi cuenta

  Scenario: Añadir una nueva dirección de envío
    Given El usuario está en la sección "Direcciones" de su cuenta
    When El usuario agrega una nueva dirección con:
      | Dirección      | Calle Falsa 123 |
      | Ciudad         | Madrid          |
      | Código Postal  | 28000           |
      | País           | España          |
    Then La dirección debería ser guardada y aparecer en la lista de direcciones del usuario

  Scenario: Editar una dirección existente
    Given El usuario tiene una dirección de envío guardada
    When El usuario edita la dirección a:
      | Dirección      | Calle Verdadera 456 |
      | Ciudad         | Madrid               |
      | Código Postal  | 28001                |
    Then La dirección debería ser actualizada correctamente en la cuenta del usuario

  Scenario: Eliminar una dirección de envío
    Given El usuario tiene una dirección de envío guardada
    When El usuario elimina la dirección
    Then La dirección debería ser eliminada y ya no aparecer en la lista de direcciones