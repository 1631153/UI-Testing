Feature: Contraseña olvidada

Scenario: Contraseña olvidada en correo valido
Given the user is in the homepage
When the user clicks to go to the login page
And the user clicks on forgotten password
And the user fills email:
    | Email        | javi.david@test.com |
And the user clicks on the continue button
Then the user should see a confirmation message on another page
    
Scenario: Contraseña olvidada en correo invalido
Given the user is in the homepage
When the user clicks to go to the login page
And the user clicks on forgotten password
And the user fills email:
    | Email        | sdfghjfds |
And the user clicks on the continue button
Then the user should see a warning message on the same page