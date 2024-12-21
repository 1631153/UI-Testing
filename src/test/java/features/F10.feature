Feature: Ordenar productos en la página de resultados
  Para que el usuario pueda encontrar productos fácilmente
  Como usuario de la tienda online
  Quiero ordenar los productos según diferentes criterios

  Scenario: Ordenar productos por precio de menor a mayor
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Precio: Menor a Mayor" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados por precio en orden ascendente

  Scenario: Ordenar productos por precio de mayor a menor
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Precio: Mayor a Menor" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados por precio en orden descendente

  Scenario: Ordenar productos por nombre de la A a la Z
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Nombre: A a Z" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados alfabéticamente de la A a la Z

  Scenario: Ordenar productos por marca de la Z a la A
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Nombre: Z a A" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados alfabéticamente de la Z a la A
    
  Scenario: Ordenar productos por marca de la A a la Z
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Marca: A a Z" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados alfabéticamente de la A a la Z

  Scenario: Ordenar productos por nombre de la Z a la A
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Marca: Z a A" en el menú de ordenación
    Then Los productos deberían mostrarse ordenados alfabéticamente de la Z a la A

  Scenario: Ordenar productos por calificación
    Given El usuario está en la página de resultados de búsqueda
    When El usuario selecciona "Calificación: Más alto primero" en el menú de ordenación
    Then Los productos deberían mostrarse con las calificaciones más altas primero

