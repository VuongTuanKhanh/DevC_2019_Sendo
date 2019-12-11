package controllers;

import play.mvc.Controller;

public class BaseController extends Controller {

    protected void setHeaderResponse(int length){
        response().setHeader("Content-Type", "application/json");
        response().setHeader("Content-Encoding", "gzip");
        response().setHeader("Content-Length", length + "");
    }
}
