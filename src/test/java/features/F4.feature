Feature: Filtrar productos
Scenario: Filtrar productos por la categoría Laptops & Notebooks

Given the user is in the homepage
When the user clicks on MacBook from Featured
And the user clicks Add to Cart button
Then the user should see the cart summary





Given the user is in the homepage
When the user hovers over "Laptops & Notebooks" in the menu
And the user clicks on "Show All Laptops & Notebooks"
Then the user should see only products related to "Laptops & Notebooks"
