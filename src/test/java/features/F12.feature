Feature: Gestión de cuenta
  Para mantener la seguridad y persistencia de los datos
  Como usuario registrado en la tienda online
  Quiero gestionar los detalles de mi cuenta, pero no puedo eliminarla

  Scenario: Actualizar la información personal
    Given El usuario está en la página de "Mi Cuenta"
    When El usuario actualiza su información personal con:
      | Nombre     | Javier        |
      | Apellido   | González      |
      | Correo     | javi@test.com |
    Then La información personal del usuario debería actualizarse correctamente

  Scenario: Cambiar la contraseña
    Given El usuario está en la página de "Cambiar Contraseña"
    When El usuario introduce su contraseña actual y una nueva contraseña:
      | Contraseña Actual | Password123 |
      | Nueva Contraseña  | NuevaPass45 |
    And El usuario confirma la nueva contraseña
    Then La contraseña del usuario debería cambiarse con éxito

  Scenario: Cambiar la dirección predeterminada
    Given El usuario está en la página de "Mis Direcciones"
    When El usuario selecciona una dirección existente y la marca como predeterminada
    Then La dirección predeterminada del usuario debería actualizarse correctamente
