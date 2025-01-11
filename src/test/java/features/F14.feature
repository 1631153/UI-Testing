Feature: Valoración de productos
  Para compartir mi experiencia con otros usuarios
  Como cliente de la tienda
  Quiero poder valorar los productos en su página correspondiente

  Scenario: Valorar un producto con estrellas
    Given El usuario está en la página del producto que desea valorar
    When El usuario accede a la sección de valoraciones
    And El usuario escribe su nombre como "Juan Pérez"
    And El usuario escribe un comentario "Buen producto, cumple con lo prometido."
    And El usuario selecciona una calificación de 4 estrellas
    And El usuario hace clic en el botón "Continue"
    Then Debería aparecer un mensaje de éxito que dice "Thank you for your review. It has been submitted to the webmaster for approval."

  Scenario: Intentar enviar una valoración sin escribir un comentario
    Given El usuario está en la página del producto que desea valorar
    When El usuario accede a la sección de valoraciones
    And El usuario escribe su nombre como "Juan Pérez"
    And El usuario selecciona una calificación de 4 estrellas
    And El usuario hace clic en el botón "Continue"
    Then Debería aparecer un mensaje de error "Warning: Review Text must be between 25 and 1000 characters!"
  
  Scenario: Intentar enviar una valoración exediendo el limite de caracteres de un comentario
    Given El usuario está en la página del producto que desea valorar
    When El usuario accede a la sección de valoraciones
    And El usuario escribe su nombre como "Juan Pérez"
    And El usuario escribe un comentario "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    And El usuario selecciona una calificación de 4 estrellas
    And El usuario hace clic en el botón "Continue"
    Then Debería aparecer un mensaje de error "Warning: Review Text must be between 25 and 1000 characters!"

  Scenario: Intentar enviar una valoración sin seleccionar una calificación
    Given El usuario está en la página del producto que desea valorar
    When El usuario accede a la sección de valoraciones
    And El usuario escribe su nombre como "Juan Pérez"
    And El usuario escribe un comentario "Buen producto, pero podría mejorar."
    And El usuario hace clic en el botón "Continue"
    Then Debería aparecer un mensaje de error "Warning: Please select a review rating!"
    
  Scenario: Intentar enviar una valoración sin escribir un nombre
    Given El usuario está en la página del producto que desea valorar
    When El usuario accede a la sección de valoraciones
    And El usuario escribe un comentario "Buen producto, pero podría mejorar."
    And El usuario selecciona una calificación de 4 estrellas
    And El usuario hace clic en el botón "Continue"
    Then Debería aparecer un mensaje de error "Warning: Review Name must be between 3 and 25 characters!"
    
  Scenario: Intentar enviar una valoración exediendo el limite de caracteres de un nombre
    Given El usuario está en la página del producto que desea valorar
    When El usuario accede a la sección de valoraciones
    And El usuario escribe su nombre como "JuanJuanJuan PérezPérezPérez"
    And El usuario escribe un comentario "Buen producto, pero podría mejorar."
    And El usuario selecciona una calificación de 4 estrellas
    And El usuario hace clic en el botón "Continue"
    Then Debería aparecer un mensaje de error "Warning: Review Name must be between 3 and 25 characters!"
