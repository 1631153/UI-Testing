Feature: Valoración de productos
  Para compartir mi experiencia con otros usuarios
  Como cliente de la tienda
  Quiero poder valorar los productos que he comprado

  Scenario: Valorar un producto con estrellas
    Given El usuario ha comprado un producto
    When El usuario deja una valoración de 4 estrellas
    Then La valoración debería ser visible en la página del producto

  Scenario: Dejar un comentario sobre un producto
    Given El usuario ha comprado un producto
    When El usuario escribe un comentario sobre el producto
    Then El comentario debería ser visible en la página del producto junto con la valoración