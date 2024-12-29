Feature: Verificar disponibilidad de productos
  Para asegurarme de que puedo comprar el producto
  Como usuario
  Quiero poder ver si un producto está disponible para comprar

  Scenario: Producto disponible en stock
    Given El usuario está en la página de un producto
    When El producto está en stock
    Then El sistema debería mostrar "Disponible" junto al producto

  Scenario: Producto agotado
    Given El usuario está en la página de un producto
    When El producto está agotado
    Then El sistema debería mostrar "Producto agotado" junto al producto

  Scenario: Agregar un producto agotado al carrito
    Given El usuario está en la página de un producto agotado
    When El usuario intenta agregar el producto al carrito
    Then El sistema debería mostrar un mensaje indicando que el producto no está disponible
