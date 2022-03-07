package com.pe.checkout.service.stepsdefinitions;

import com.pe.checkout.service.model.Payment;
import com.pe.checkout.service.questions.ResponseCode;
import com.pe.checkout.service.questions.TheTransaction;
import com.pe.checkout.service.tasks.PayYape;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;

import static org.hamcrest.CoreMatchers.equalTo;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PayYapeStepsDefinitions {

    Payment pay;

    EnvironmentVariables environmentVariables;

    @Before
    public void setthePage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^el yapero invoca al EP Pay$")
    public void elYaperoInvocaAlEPPay() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("url")));
    }


    @And("^el yapero ingresa su numero (.*) y el monto (.*) a pagar en el commerce$")
    public void elYaperoIngresaSuNumeroCelularYElMontoMontoAPagarEnElCommerce(String celular, String monto) {
        theActorInTheSpotlight().attemptsTo(PayYape.with(celular, monto));
    }

    @Then("^el yapero confirma la transaccion$")
    public void elYaperoConfirmaLaTransaccion() {
        theActorInTheSpotlight().should(seeThat(new ResponseCode(), equalTo(200)));
    }

    @Then("^el yapero visualiza el codigo (.*) y descripcion (.*)$")
    public void elYaperoVisualizaElCodigoCodigoYDescripcionDescripcion(String codigo, String descripcion) {
        pay = new TheTransaction().answeredBy(theActorInTheSpotlight());
        System.out.println(pay.getCode() + "---------------");
        System.out.println(pay.getDescription() + "---------------");
        System.out.println(pay.getErrorType() + "---------------");
        theActorInTheSpotlight().should(seeThat("El codigo de error es: ", actor -> pay.getCode(), equalTo(codigo)),
                seeThat("La descripcion es: ", actor -> pay.getDescription(), equalTo(descripcion)));
    }
}
