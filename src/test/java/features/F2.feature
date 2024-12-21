Feature: Inicio de sesión

  Scenario: Inicio de sesión en la página
    Given El usuario está en la página de inicio para iniciar sesión
    When El usuario hace clic para ir a la página de inicio de sesión
    And El usuario ingresa el correo electrónico y la contraseña:
      | Email                  | javi.david@test.com |
      | Password               | Password123         |
    And El usuario hace clic en el botón de Iniciar sesión
    Then El usuario debería ver la página de su cuenta