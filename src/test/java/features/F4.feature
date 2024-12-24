Feature: Cambio de moneda
Scenario: Cambiar la moneda a euros

Given the user is in the homepage
When the user clicks on the currency dropdown to select Euro
Then the prices on the homepage should be all displayed in euros