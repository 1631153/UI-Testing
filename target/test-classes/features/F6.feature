Feature: Comparar productos

Scenario: Comparar dos productos
Given the user is in the homepage
When the user searches for Laptop & Notebooks
And the user clicks compare for HP
And the user clicks compare for MacBook
And the user navigates to the product comparison page
And Add To Cart the better one
Then the best laptop is in the shopping cart