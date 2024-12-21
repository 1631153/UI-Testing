Feature: Guardar productos en la lista de deseos
  Para poder comprarlos más tarde
  Como usuario
  Quiero poder agregar productos a mi lista de deseos

  Scenario: Agregar un producto a la lista de deseos
    Given El usuario está en la página de un producto
    When El usuario hace clic en "Agregar a la lista de deseos"
    Then El producto debería ser agregado a la lista de deseos del usuario

  Scenario: Ver productos en la lista de deseos
    Given El usuario ha agregado productos a su lista de deseos
    When El usuario navega a la sección "Lista de deseos"
    Then El usuario debería ver los productos guardados en su lista de deseos

  Scenario: Eliminar un producto de la lista de deseos
    Given El usuario tiene productos en su lista de deseos
    When El usuario elimina un producto de la lista
    Then El producto debería ser eliminado de la lista de deseos
