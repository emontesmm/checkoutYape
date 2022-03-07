package com.pe.checkout.service.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static com.pe.checkout.service.utils.WebServiceEndpoints.TOKEN_RESOURCE;

public class TokenConsult implements Task {

 /*   private final String username;
    private final String password;
    private final String grant_type;
    private final String scope;
    private final String client_id;
    private final String response_type;
    private final String sub;
    private final String document;
    private final String names;
    private final String fullName;
    private final String needName;
    private final String users;
    private final String uuid;
    private final String documentType;
    private final String idYapeAccount;


    public ObtainToken(String username, String password, String grant_type, String scope, String client_id, String response_type, String sub, String document, String names, String fullName, String needName, String users, String uuid, String documentType, String idYapeAccount) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
        this.scope = scope;
        this.client_id = client_id;
        this.response_type = response_type;
        this.sub = sub;
        this.document = document;
        this.names = names;
        this.fullName = fullName;
        this.needName = needName;
        this.users = users;
        this.uuid = uuid;
        this.documentType = documentType;
        this.idYapeAccount = idYapeAccount;
    }*/

    public static String access_token;

    @Override
    @Step("{0} obtain token")
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> params = new HashMap<String,String>();
        
        params.put("username", "stephanydanerib10@mailinator.com");
        params.put("password", "9d78qnZlwjFT%2B7Ha9SxXDBNCyT1YRwKyaTvDWkBKwtM%3D");
        params.put("grant_type", "password");
        params.put("scope", "openid%20e86662b3-6dd3-40a2-bfc2-3cc5aa314a6c");
        params.put("client_id", "e86662b3-6dd3-40a2-bfc2-3cc5aa314a6c");
        params.put("response_type", "token");
        params.put("sub", "fhfg");
        params.put("document", "doc");
        params.put("names", "namesx");
        params.put("fullName", "fullnamex");
        params.put("needName", "neednamex");
        params.put("users", "usersxd");
        params.put("uuid", "13ddsds");
        params.put("documentType", "fdf");
        params.put("idYapeAccount", "e1610c190b6a5581839546844632096c");

        actor.attemptsTo(Post.to(TOKEN_RESOURCE.getUrl()).with(requestSpecification -> requestSpecification.given().log().all().urlEncodingEnabled(false).contentType("text/html")
                .and().queryParams(params).and().header("Cookie", "x-ms-cpim-sso:yapedev.onmicrosoft.com_0=m1.hAun06NcLQ83Cj/x.2x3pgibxKCNj7wT1gWrXQA==.0.XIwdw26K7xK1LVGVRa0Mp4PJ1P7CEei748R+0IJ/xKwdp5HhEieYZefQT9CRAqVFM3+ckLh9ouEHQmdw6ol7+2+qPU5lGZaBDP/JiwcNFHNylgFdBqUSAQv6NZPo7V3nndGnK3kXuyERz/666uIAeX2+pklLzIZ9ZADYJpn3HtKtkuCqrdJHfDKSh5Nxd+PnH28qKZQvQiA2BQW3XfjUCJQ7DuJfzL+9//5ajQ==; x-ms-cpim-sso:yapedev.onmicrosoft.com_0=m1.TQIj7ltp09/CX9FM.bvjHOuRoww2meEZdwuMwaQ==.0.YxFiozOqMtKBK6rWjZozgbTmCZ9i9XHiMLOaollcx6YD2/djjvX0blV//ZGChf37Gwl+hpOukjHTtjgvm7GQpae3fLxc/dVKVv/2PjOwFXu6tSqac5i7JP6juTGSMTqkdZMy3wki6JdHgh60Ngu5PiTivoB6qooMgQnkDjee5mwppDBdV/XctxqchOsPUL8pQIw/Ilrt8t5D1mM2bRtMrNH30BAlHuRUeQ983+cq; x-ms-cpim-sso:yapedev.onmicrosoft.com_0=m1.uR9rlF9pnh1xEj6l.9/yOWZBMJ3JELx+IQrTzQg==.0.Z8Fo0QnZUQreDYYSwcXeHFO3yAT195d9lwQpYDa9ldpQ17LzIstrJwV6q7/5Ql16UtMyXcbi7imvcNJS1bIvpqnxlwRkfYyh+1wQvJznsrmsIZm5Q30RsBUKr9XQP+Goa3l2O8LI44wCgQ7gxR2CSH0dID0gmfGv2GLriMhplIal6jLEDsqkrBxm4QjkzAiQtmENvZZW7wQIKRzMu+myOavToB/efW2YYBsH4Q==; x-ms-cpim-sso:yapedev.onmicrosoft.com_0=m1.C8Fv81UxDHLzolsM.p3Q7SeaMoRnLTHJw5kLrxA==.0.+kgmNjcdpucCeonzvscpB5q3RlL6D1P9lWrCxKFIstS4ZYmPGDb3fuZlIW+igfw6l9YKqMqbUXt3YT8KntWnGQOwjCUd94144D6s8rae2W8E/MfBAPffFE8px0sfQv3eKkYfncLRlqI+dYY3r/pwsHUqGHYF+QLCms9v/82PpUj4zCuKfn1XMsDOnXaiZmeRmeDJA3HWQb8qVD88IjsCTtR8MSDabq54WoJQt4co")));
        System.out.println("--------------***************");
        SerenityRest.then().log().all();

        access_token = SerenityRest.lastResponse().jsonPath().getString("access_token");

    }

    public static TokenConsult obtain() {
        return instrumented(TokenConsult.class);
    }
}
