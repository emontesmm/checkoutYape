package com.pe.checkout.service.tasks;


import com.google.gson.JsonObject;
import com.pe.checkout.service.utils.Utils;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.pe.checkout.service.utils.WebServiceEndpoints.PAY_RESOURCE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PayYape implements Task {
    Utils utils = new Utils();

    private final String phoneNumber;
    private final String amount;

    public PayYape(String phoneNumber, String amount) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public static Performable with(String phoneNumber, String amount) {
        return instrumented(PayYape.class, phoneNumber, amount);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String file = "paycheckout.json";
        JsonObject checkoutJson = null;
        try {
            checkoutJson = utils.getJsonFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Request-ID", "550e8400-e29b-41d4-a716-447655440000"));
        headerlist.add(new Header("request-date", "2015-06-01T17:15:20.509-0400"));
        headerlist.add(new Header("app-code", "YP"));
        headerlist.add(new Header("caller-name", "terms-conditions"));
        headerlist.add(new Header("Authorization", "Bearer " + TokenConsult.access_token));
        Headers headers = new Headers(headerlist);

        JsonObject dataCheckoutJson = checkoutJson;
        dataCheckoutJson.addProperty("phoneNumber", phoneNumber);
        dataCheckoutJson.addProperty("amount", amount);

        actor.attemptsTo(Post.to(PAY_RESOURCE.getUrl()).with(requestSpecification -> requestSpecification.given().log().all()
                .contentType("application/json").and().body(dataCheckoutJson.toString()).headers(headers)));
    }
}
