Feature: Gestión de devoluciones

Scenario: Devolver un MacBook que acaba de comprar
Given the user is in the homepage
When the user buys a product
And the user goes to Order History in My Account
And the user opens the details of the last order
And the user clicks on the return button
And the user fill the obligatory options to return the product
Then the user should see in the Return page the product
