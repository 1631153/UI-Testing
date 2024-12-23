Feature: Inicio de sesión
Scenario: Inicio de sesión en la pagina

Given the user is in the homepage
When the user clicks to go to the login page
And the user fills email and password:
    | Email        | javi.david@test.com |
    | Password     | Password123   |
And the user clicks on the Login button
Then the user should see the account page