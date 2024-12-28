Feature: Registro de usuario

Scenario: Registro exitoso de un nuevo usuario
Given the user is in the homepage
When the user clicks to go to the registration page
And the user fills in the registration form with the following details:
    | First Name   | Javi          |
    | Last Name    | David           |
    | Email        | javi.david@test.com |
    | Telephone    | 123456789     |
    | Password     | Password123   |
    | Confirm      | Password123   |
And the user agrees to the privacy policy
And the user clicks on the continue button
Then the user should see a confirmation message

Scenario: Registro fallido de un nuevo usuario (nombre y telefono)
Given the user is in the homepage
When the user clicks to go to the registration page
And the user fills in the registration form with the following details:
    | First Name   | 1631153          |
    | Last Name    | David           |
    | Email        | f@f.com |
    | Telephone    | 12345d6789     |
    | Password     | Password123   |
    | Confirm      | Password123   |
And the user agrees to the privacy policy
And the user clicks on the continue button
Then the user should see a email warning message

Scenario: Registro fallido de un nuevo usuario (email)
Given the user is in the homepage
When the user clicks to go to the registration page
And the user fills in the registration form with the following details:
    | First Name   | 1631153          |
    | Last Name    | David           |
    | Email        | sukabliat.com |
    | Telephone    | 12345d6789     |
    | Password     | Password123   |
    | Confirm      | Password123   |
And the user clicks on the continue button
And the user should see a private policy warning message
Then the user should be in the same page