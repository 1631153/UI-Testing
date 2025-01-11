Feature: Ordenar productos en la página de resultados
  Para que el usuario pueda encontrar productos fácilmente
  Como usuario de la tienda online
  Quiero ordenar los productos según diferentes criterios

  Scenario: Ordenar productos por precio de menor a mayor
    Given El usuario está en la categoria de Desktops
    When El usuario selecciona "Price (Low > High)" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados por precio en orden ascendente

  Scenario: Ordenar productos por precio de mayor a menor
    Given El usuario está en la categoria de Desktops
    When El usuario selecciona "Price (High > Low)" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados por precio en orden descendente

  Scenario: Ordenar productos por nombre de la A a la Z
    Given El usuario está en la categoria de Desktops
    When El usuario selecciona "Name (A - Z)" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados alfabéticamente de la A a la Z

  Scenario: Ordenar productos por nombre de la Z a la A
    Given El usuario está en la categoria de Desktops
    When El usuario selecciona "Name (Z - A)" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados alfabéticamente de la Z a la A

  Scenario: Ordenar productos por calificación decendentes
    Given El usuario está en la categoria de Desktops
    When El usuario selecciona "Rating (Highest)" en el menú de ordenación
    Then Los productos deberían mostrarse con las calificaciones más altas primero

  Scenario: Ordenar productos por calificación acendentes
    Given El usuario está en la categoria de Desktops
    When El usuario selecciona "Rating (Lowest)" en el menú de ordenación
    Then Los productos deberían mostrarse con las calificaciones más bajas primero