Feature: Gestión de la información personal de la cuenta
  Para mantener la seguridad y persistencia de los datos
  Como usuario registrado en la tienda online
  Quiero gestionar los detalles de mi cuenta, pero no puedo eliminarla

  # Escenario para actualizar la información personal
  Scenario: Actualizar la información personal
    Given El usuario está logeado en su cuenta 2
      | Email                  | javi.david@test.com |
      | Password               | Password123         |
    When El usuario hace clic en el enlace "Edit Account" 2
    And El usuario actualiza su información personal con
      | Nombre     | Javier         |
      | Apellido   | González       |
      | Correo     | david.javi@test.com  |
      | Telefono   | 987654321      |
    Then El mensaje de éxito debería aparecer
    And El usuario hace clic en el enlace "Edit Account" 2
    And La información personal del usuario debería actualizarse correctamente

  # Escenario para restaurar la información personal a los valores originales
  Scenario: Restaurar la información personal a los valores originales
    Given El usuario está logeado en su cuenta 2
      | Email                  | david.javi@test.com |
      | Password               | Password123   |
    When El usuario hace clic en el enlace "Edit Account" 2
    And El usuario actualiza su información personal con
      | Nombre     | Javi           |
      | Apellido   | David          |
      | Correo     | javi.david@test.com |
      | Telefono   | 123456789      |
    Then El mensaje de éxito debería aparecer
    And El usuario hace clic en el enlace "Edit Account" 2
    And La información personal del usuario debería restaurarse correctamente
   
  # Escenario para actualizar la información personal con datos inválidos
  Scenario: Actualizar la información personal con datos inválidos
    Given El usuario está logeado en su cuenta 2
      | Email                  | javi.david@test.com |
      | Password               | Password123         |
    When El usuario hace clic en el enlace "Edit Account" 2
    And El usuario deja los campos vacíos y hace clic en "Continue"
    Then El sistema debería mostrar los mensajes de error correspondientes
