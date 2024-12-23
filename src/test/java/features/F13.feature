Feature: Gestionar direcciones de envío
  Para recibir mis pedidos en la dirección correcta
  Como usuario de la tienda
  Quiero poder agregar, editar y eliminar direcciones de envío en mi cuenta

  # Escenario para iniciar sesión antes de gestionar las direcciones
  Scenario: Iniciar sesión antes de gestionar direcciones
    Given El usuario está en la página de inicio para iniciar sesión
    When El usuario hace clic para ir a la página de inicio de sesión
    And El usuario ingresa el correo electrónico y la contraseña:
      | Email                  | javi.david@test.com |
      | Password               | Password123         |
    And El usuario hace clic en el botón de Iniciar sesión
    Then El usuario debería ver la página de su cuenta

  # Escenario para añadir una nueva dirección de envío
  Scenario: Añadir una nueva dirección de envío
    Given El usuario está logeado en su cuenta
    And El usuario está en la sección "Direcciones" de su cuenta
    When El usuario agrega una nueva dirección con:
      | Dirección      | Calle Falsa 123 |
      | Ciudad         | Madrid          |
      | Código Postal  | 28000           |
      | País           | España          |
    Then La dirección debería ser guardada y aparecer en la lista de direcciones del usuario

  # Escenario para editar una dirección existente
  Scenario: Editar una dirección existente
    Given El usuario está logeado en su cuenta
    And El usuario tiene una dirección de envío guardada
    When El usuario edita la dirección a:
      | Dirección      | Calle Verdadera 456 |
      | Ciudad         | Madrid               |
      | Código Postal  | 28001                |
    Then La dirección debería ser actualizada correctamente en la cuenta del usuario

  # Escenario para eliminar una dirección de envío
  Scenario: Eliminar una dirección de envío
    Given El usuario está logeado en su cuenta
    And El usuario tiene una dirección de envío guardada
    When El usuario elimina la dirección
    Then La dirección debería ser eliminada y ya no aparecer en la lista de direcciones