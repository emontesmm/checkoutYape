Feature: Funcionalidad de Checkout Ecommerce
  Rule: Yo como usuario necesito realizar el pago con Yape checkout desde el boton de pagos del ecommerce

    Background: Token obtain
      Given que el yapero obtiene el token

    Scenario Outline: Validar que el EP Pay responda correctamente
      When el yapero invoca al EP Pay
      And el yapero ingresa su numero <celular> y el monto <monto> a pagar en el commerce
      Then el yapero confirma la transaccion
      Examples:
        | celular   | monto |
        | 982050051 | 50.00 |

    Scenario Outline: Validar que el EP Pay no responda correctamente de acuerdo a las validaciones
      When el yapero invoca al EP Pay
      And el yapero ingresa su numero <celular> y el monto <monto> a pagar en el commerce
      Then el yapero visualiza el codigo <codigo> y descripcion <descripcion>
      Examples: YAPERO NO EXISTE
        | celular   | monto | codigo    | descripcion                                                                  |
        | 969929151 | 50.00 | YPCHK0001 | Conflicto entre el recurso a actualizar y el que se encuentra en el sistema. |
      Examples: YAPERO NO ACTIVO
        | celular   | monto | codigo    | descripcion                                                                  |
        | 934400002 | 50.00 | YPCHK0001 | Conflicto entre el recurso a actualizar y el que se encuentra en el sistema. |
      Examples: YAPERO EN BLACKLIST
        | celular   | monto | codigo    | descripcion                                                                  |
        | 982050052 | 50.00 | YPCHK0002 | Conflicto entre el recurso a actualizar y el que se encuentra en el sistema. |
      Examples: YAPERO PAGA MAYOR AL LIMITE DIARIO DE TRANSACCION
        | celular   | monto  | codigo    | descripcion                                                                  |
        | 982050051 | 600.00 | YPCHK0003 | Conflicto entre el recurso a actualizar y el que se encuentra en el sistema. |
