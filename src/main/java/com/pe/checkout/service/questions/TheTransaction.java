package com.pe.checkout.service.questions;

import com.pe.checkout.service.model.Payment;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheTransaction implements Question {

    @Override
    public Payment answeredBy(Actor actor) {
        return  SerenityRest.lastResponse().getBody().as(Payment.class);
    }
}
