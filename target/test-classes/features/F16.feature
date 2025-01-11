Feature: Suscripción a boletín de noticias
  Para recibir las últimas ofertas y novedades
  Como usuario
  Quiero poder suscribirme y desuscribirme al boletín de noticias

  Scenario: Suscribirse al boletín de noticias
    Given El usuario está logeado en su cuenta
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el enlace "Newsletter"
    And El usuario hace clic en "Yes"
    Then Debería aparecer un mensaje de éxito que dice "Success: Your newsletter subscription has been successfully updated!"

  Scenario: Desuscribirse al boletín de noticias
    Given El usuario está logeado en su cuenta
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el enlace "Newsletter"
    And El usuario hace clic en "No"
    Then Debería aparecer un mensaje de éxito que dice "Success: Your newsletter subscription has been successfully updated!"
