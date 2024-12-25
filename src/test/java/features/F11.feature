Feature: Gestión de la contraseña de la cuenta
  Para mantener la seguridad de la cuenta
  Como usuario registrado en la tienda online
  Quiero cambiar mi contraseña para proteger mi cuenta

  # Escenario para cambiar la contraseña
  Scenario: Cambiar la contraseña
    Given El usuario está logeado en su cuenta
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Password"
    And El usuario introduce una nueva contraseña:
      | input-password         | NuevaPass45         |
    And El usuario introduce la confirmación de la nueva contraseña:
      | input-confirm          | NuevaPass45         |
    And El usuario hace clic en el botón de continuar para guardar la nueva contraseña
    Then La contraseña del usuario debería cambiarse con éxito

  # Escenario para restaurar la contraseña a su valor original
  Scenario: Restaurar la contraseña a su valor original
    Given El usuario está logeado en su cuenta
      | input-email            | javi.david@test.com |
      | input-password         | NuevaPass45         |
    When El usuario hace clic en el enlace "Password"
    And El usuario introduce una nueva contraseña:
      | input-password         | Password123         |
    And El usuario introduce la confirmación de la nueva contraseña:
      | input-confirm          | Password123         |
    And El usuario hace clic en el botón de continuar para guardar la nueva contraseña
    Then La contraseña del usuario debería cambiarse con éxito

	# Escenario para intentar cambiar la contraseña sin confirmar la nueva contraseña
  Scenario: Intentar cambiar la contraseña sin confirmar la nueva contraseña
    Given El usuario está logeado en su cuenta
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Password"
    And El usuario introduce una nueva contraseña:
      | input-password         | NuevaPass45         |
		And El usuario hace clic en el botón de continuar para guardar la nueva contraseña
    Then El sistema debería mostrar un mensaje de error indicando que las contraseñas no coinciden

  # Escenario para intentar cambiar la contraseña con contraseñas que no coinciden
  Scenario: Intentar cambiar la contraseña con contraseñas que no coinciden
    Given El usuario está logeado en su cuenta
      | input-email            | javi.david@test.com |
      | input-password         | Password123         |
    When El usuario hace clic en el enlace "Password"
    And El usuario introduce una nueva contraseña:
      | input-password         | NuevaPass45         |
    And El usuario introduce la confirmación de la nueva contraseña:
      | input-confirm          | NuevaPass46         |
    And El usuario hace clic en el botón de continuar para guardar la nueva contraseña
    Then El sistema debería mostrar un mensaje de error indicando que las contraseñas no coinciden