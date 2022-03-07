package com.pe.checkout.service.stepsdefinitions;

import com.pe.checkout.service.tasks.TokenConsult;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AccessTokenStepsDefinitions {
    EnvironmentVariables environmentVariables;

    @Before
    public void setthePage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^que el (.*) obtiene el token$")
    public void queElYaperoObtieneElToken(String yapero) {
        theActorCalled(yapero).whoCan(CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("token")))
                .attemptsTo(TokenConsult.obtain());


    }
}
