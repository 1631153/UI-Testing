Feature: Suscripción a boletín de noticias
  Para recibir las últimas ofertas y novedades
  Como usuario
  Quiero poder suscribirme al boletín de noticias

  Scenario: Suscribirse al boletín de noticias
    Given El usuario está en la página de inicio
    When El usuario ingresa su correo electrónico "javi@test.com" en el campo de suscripción
    And El usuario hace clic en "Suscribirse"
    Then El sistema debería confirmar la suscripción con un mensaje de éxito

  Scenario: Intentar suscribirse con un correo electrónico inválido
    Given El usuario está en la página de inicio
    When El usuario ingresa un correo electrónico inválido "notavalido@com"
    And El usuario hace clic en "Suscribirse"
    Then El sistema debería mostrar un mensaje de error indicando que el correo no es válido
