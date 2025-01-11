Feature: Comprar como invitado

Scenario: Realizar una compra como invitado
  Given the user is in the homepage
  When the user add products to the Shopping Cart
  And the user buys the product with the Guess option
  Then the user should be able to enter the shipping details witout an account

Scenario: Intentar realizar una compra sin proporcionar datos de envío
  Given the user is in the homepage
  When the user add products to the Shopping Cart
  And the user tries to proceed without a shipping details
  Then warning messages about the shipping details should appear
