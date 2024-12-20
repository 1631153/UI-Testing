Feature: Buscar productos en OpenCart

  Scenario: Buscar un producto existente
    Given El usuario está en la página de inicio para buscar
    When Introduce "iPhone" en la barra de búsqueda
    And Hace clic en el botón de buscar
    Then Aparecen resultados relevantes relacionados con "iPhone"

  Scenario: Buscar un producto inexistente
    Given El usuario está en la página de inicio para buscar
    When Introduce "XYZ123" en la barra de búsqueda
    And Hace clic en el botón de buscar
    Then No aparecen resultados y se muestra un mensaje de "Your shopping cart is empty!"
