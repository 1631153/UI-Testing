Feature: Verificar datos de facturación
  Para asegurarme de que los datos en mi factura son correctos y están actualizados
  Como usuario
  Quiero poder ver y modificar mis datos de facturación, incluida la fecha de emisión

  Scenario: Ver datos de facturación en la página de pago
    Given El usuario ha agregado productos al carrito y va a la página de pago
    When El usuario revisa sus datos de facturación
    Then Los datos de facturación deberían estar completos y correctos
    And La fecha de emisión de la factura debería estar presente y ser correcta

  Scenario: Verificar fecha de emisión en los datos de facturación
    Given El usuario está en la página de pago
    When El usuario revisa la fecha de emisión de la factura
    Then La fecha de emisión debería ser la fecha actual o la más reciente según corresponda

  Scenario: Modificar datos de facturación y verificar la fecha de pago
    Given El usuario está en la página de pago
    When El usuario actualiza su dirección de facturación y procede con el pago
    Then Los datos de facturación deben ser actualizados correctamente
    And La fecha de pago debería ser la fecha actual

  Scenario: Verificar la fecha de vencimiento de la factura
    Given El usuario está revisando los datos de su factura
    When El usuario observa la fecha de vencimiento de la factura
    Then La fecha de vencimiento debería ser coherente con las condiciones de pago del producto
