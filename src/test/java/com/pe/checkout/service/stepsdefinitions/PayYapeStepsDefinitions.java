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
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PayYapeStepsDefinitions {

    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8081;
    private static final String ZAP_API_KEY =
            null; // Change this if you have set the apikey in ZAP via Options / API

    private static final String TARGET = "https://azfdeu2yaped10.azurefd.net/yap-che/channel/yape/v1/checkout/pay";


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

    @Then("el ejecuto")
    public void elEjecuto() throws ClientApiException, IOException {
        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);

        try {
            // Start spidering the target
            System.out.println("Spider : " + TARGET);
            // It's not necessary to pass the ZAP API key again, already set when creating the
            // ClientApi.
            ApiResponse resp = api.spider.scan(TARGET, null, null, null, null);
            String scanid;
            int progress;

            // The scan now returns a scan id to support concurrent scanning
            scanid = ((ApiResponseElement) resp).getValue();

            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.spider.status(scanid)).getValue());
                System.out.println("Spider progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            System.out.println("Spider complete");

            // Poll the number of records the passive scanner still has to scan until it completes
            while (true) {
                Thread.sleep(1000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.pscan.recordsToScan()).getValue());
                System.out.println("Passive Scan progress : " + progress + " records left");
                if (progress < 1) {
                    break;
                }
            }
            System.out.println("Passive Scan complete");

            System.out.println("Active scan : " + TARGET);
            resp = api.ascan.scan(TARGET, "True", "False", null, null, null);

            // The scan now returns a scan id to support concurrent scanning
            scanid = ((ApiResponseElement) resp).getValue();

            // Poll the status until it completes
            while (true) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.ascan.status(scanid)).getValue());
                System.out.println("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            System.out.println("Active Scan complete");

            System.out.println("Alerts:");
            System.out.println(new String(api.core.xmlreport(), StandardCharsets.UTF_8));



        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }

        String Report = new String(api.core.htmlreport(), StandardCharsets.UTF_8);
        System.out.println(System.getProperty("user.dir"));
        Path filePath = Paths.get(System.getProperty("user.dir") + "/scan-results/seleniumTestsapi.html");
        if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
            Files.createFile(filePath);
        }
        Files.write(filePath, Report.getBytes());
    }


}
