Feature: Carrito de compras

Scenario: Añadir un producto al carrito
Given the user is in the homepage
When the user clicks on MacBook from Featured
And the user clicks Add to Cart button
Then the user should see the cart summary

Scenario: El usuario se loggea
Given the user is on the login page
And the user fills email and password:
    | Email        | javi.david@test.com |
    | Password     | Password123   |
And the user clicks on the Login button
Then the user should see the account page

Scenario: Comprar un producto
Given the user is in the Shopping Cart page
When the user clicks on Checkout button
And the user clicks on the continue button
And the user agrees to the privacy policy
And the user clicks on another continue button
And the user clicks on the Confirm Order button
Then the user should see an order confirmation message

Scenario: Confirmar compra
Given the user is on My Account page
When the user clicks on Order History
Then the user should see the date of the last product to confirm