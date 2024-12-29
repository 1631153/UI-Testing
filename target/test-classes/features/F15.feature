Feature: Guardar productos en la lista de deseos
  Para poder comprarlos más tarde o realizar un seguimiento
  Como usuario
  Quiero poder agregar productos a mi lista de deseos, ver los productos guardados y eliminarlos desde la sección de lista de deseos.

  Scenario: Agregar un producto a la lista de deseos
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    And El usuario está en la página de detalles de un producto
    When El usuario hace clic en el botón "Add to Wish List" para anadir el producto a lista de deseos
    Then El sistema debería mostrar una notificación confirmando que el producto "HP LP3065" ha sido agregado

  Scenario: Verificar el producto en la lista de deseos
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el botón "wishlist-total" para ir a la lista de deseos
    Then La lista debería mostrar el producto "HP LP3065" previamente agregado
    
	Scenario: Agregar nuevamente un producto ya en la lista de deseos
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    And El usuario está en la página de detalles de un producto
    When El usuario hace clic en el botón "Add to Wish List" para anadir el producto a lista de deseos
    Then El sistema debería mostrar una notificación confirmando que el producto "HP LP3065" ha sido agregado

  Scenario: Verificar que el producto no se duplica
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el botón "wishlist-total" para ir a la lista de deseos
    Then La lista de deseos no debería contener duplicados de "HP LP3065"

  Scenario: Agregar un producto diferente a la lista de deseos
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    And El usuario está en la página de detalles de un producto diferente
    When El usuario hace clic en el botón "Add to Wish List" para anadir el producto a lista de deseos
    Then El sistema debería mostrar una notificación confirmando que el producto "Samsung Galaxy Tab 10.1" ha sido agregado

  Scenario: Verificar que ambos productos están en la lista de deseos
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el botón "wishlist-total" para ir a la lista de deseos
    Then La lista debería mostrar el producto "HP LP3065" previamente agregado
    And  La lista debería mostrar el producto "Samsung Galaxy Tab 10.1" previamente agregado

  Scenario: Eliminar un producto de la lista de deseos
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el botón "wishlist-total" para ir a la lista de deseos
    And El usuario hace clic en el botón "Remove" junto al producto "HP LP3065"
    Then El producto "HP LP3065" debería ser eliminado de la lista de deseos
    And El sistema debería mostrar una notificación confirmando que el producto ha sido eliminado
    
  Scenario: Eliminar todos los productos de la lista de deseos y verificar la lista vacía
    Given El usuario está logeado en su cuenta deseos
      | input-email    | javi.david@test.com |
      | input-password | Password123         |
    When El usuario hace clic en el botón "wishlist-total" para ir a la lista de deseos
    And El usuario hace clic en el botón "Remove" junto al producto "Samsung Galaxy Tab 10.1"
    Then El producto "Samsung Galaxy Tab 10.1" debería ser eliminado de la lista de deseos
    And El sistema debería mostrar una notificación confirmando que el producto ha sido eliminado
    And El sistema debería mostrar un mensaje indicando "Your shopping cart is empty!" cuando no haya productos en la lista