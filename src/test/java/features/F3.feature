Feature: Carrito de compras
Scenario: Añadir un producto al carrito y verificar el total

Given the user is in the homepage
When the user clicks on MacBook from Featured
And the user clicks Add to Cart button
Then the user should see the cart summary