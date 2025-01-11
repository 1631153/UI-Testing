Feature: Carrito de compras

Scenario: Añadir un producto al carrito
Given the user is in the homepage
And the user logs
When the user clicks on MacBook from Featured
And the user clicks Add to Cart button
Then the user should see the cart summary

Scenario: Comprar un producto
Given the user is in the homepage
And the user logs
When the user is in the Shopping Cart page
And the user clicks on Checkout button
And the user clicks on the continue button
And the user agrees to the privacy policy
And the user clicks on another continue button
And the user clicks on the Confirm Order button
Then the user should see an order confirmation message

Scenario: Confirmar compra
Given the user is in the homepage
And the user logs
When the user is on My Account page
And the user clicks on Order History
Then the user should see the date of the last product to confirm

Scenario: Eliminar productos del carrito (uno solo)
Given the user is in the homepage
When the user have a product in the Shopping Cart
And the user removes one product
Then the product should disappear from the cart

Scenario: Eliminar productos del carrito (todos)
Given the user is in the homepage
When the user have multiple products in the Shopping Cart
And the user removes one by one
Then the product should disappear from the cart
