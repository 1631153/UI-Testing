Feature: Comprar como invitado
  Para hacer una compra sin necesidad de registrarme
  Como usuario no registrado
  Quiero poder realizar una compra como invitado

  Scenario: Realizar una compra como invitado
    Given El usuario ha agregado productos al carrito
    When El usuario selecciona la opción "Comprar como invitado"
    Then El usuario debería poder ingresar los detalles de envío y proceder con el pago sin crear una cuenta

  Scenario: Intentar realizar una compra sin proporcionar datos de envío
    Given El usuario ha agregado productos al carrito
    When El usuario intenta proceder sin ingresar la dirección de envío
    Then El sistema debería mostrar un mensaje pidiendo la dirección de envío