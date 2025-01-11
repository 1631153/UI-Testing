Feature: Cambio de moneda

Scenario: Cambiar la moneda a euros
Given the user is in the homepage
When the user clicks on the currency dropdown to select "EUR"
Then the prices on the homepage should be displayed all in "€"

Scenario: Cambiar la moneda a libras
Given the user is in the homepage
When the user clicks on the currency dropdown to select "GBP"
Then the prices on the homepage should be displayed all in "£"

Scenario: Cambiar la moneda a dolares
Given the user is in the homepage
When the user clicks on the currency dropdown to select "USD"
Then the prices on the homepage should be displayed all in "$"