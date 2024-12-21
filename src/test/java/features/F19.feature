Feature: Contactar con soporte al cliente
  Para resolver problemas con mi pedido o cuenta
  Como usuario
  Quiero poder contactar con el soporte al cliente

  Scenario: Enviar un mensaje al soporte
    Given El usuario está en la página de contacto
    When El usuario ingresa su nombre, correo electrónico y mensaje
    And El usuario hace clic en "Enviar"
    Then El sistema debería enviar el mensaje y mostrar un mensaje de confirmación

  Scenario: Intentar enviar un mensaje sin completar el formulario
    Given El usuario está en la página de contacto
    When El usuario intenta enviar el formulario sin completar los campos obligatorios
    Then El sistema debería mostrar un mensaje de error indicando los campos faltantes
