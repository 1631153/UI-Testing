Feature: Eliminación de productos del carrito
  Para gestionar los productos en el carrito de compras
  Como usuario de la tienda online
  Quiero poder eliminar productos del carrito de forma individual

  Scenario: Eliminar un solo producto del carrito
    Given El usuario tiene productos en el carrito
    When El usuario elimina un producto específico
    Then El producto debería desaparecer del carrito
    And El total del carrito debería actualizarse correctamente

  Scenario: Eliminar todos los productos eliminándolos uno a uno
    Given El usuario tiene múltiples productos en el carrito
    When El usuario elimina los productos uno por uno
    Then El carrito debería estar vacío
    And El total del carrito debería ser "0"

  Scenario: Ver mensaje al eliminar el último producto
    Given El usuario tiene un solo producto en el carrito
    When El usuario elimina ese producto
    Then El carrito debería mostrar el mensaje "Tu carrito está vacío"