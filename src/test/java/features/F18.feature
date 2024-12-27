Feature: Suscripción a boletín de noticias
  Para recibir las últimas ofertas y novedades
  Como usuario
  Quiero poder suscribirme y desuscribirme al boletín de noticias

  Scenario: Suscribirse al boletín de noticias
    Given El usuario está logeado en su cuenta 3
    When El usuario hace clic en el enlace "Newsletter" 3
    And El usuario hace clic en "Yes"
    Then El sistema debería confirmar con un mensaje de éxito

  Scenario: Desuscribirse al boletín de noticias
    Given El usuario está logeado en su cuenta 3
    When El usuario hace clic en el enlace "Newsletter" 3
    And El usuario hace clic en "No"
    Then El sistema debería confirmar con un mensaje de éxito
