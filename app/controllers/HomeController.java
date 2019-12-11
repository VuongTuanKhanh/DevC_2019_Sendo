package controllers;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;
import play.libs.ws.WSClient;
import play.mvc.*;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private WSClient ws;


//    @Inject
//    public HomeController(){
//    }

    @Inject
    public HomeController(WSClient ws){
        this.ws = ws;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
//    public Result index() {
//        return ok(index.render("Your new application is ready."));
//    }

    public Result index() throws Exception {
        Logger.info("---------Home-----------");
        Logger.info(BCrypt.hashpw("mimicute", BCrypt.gensalt()));

        return ok(views.html.element.render());
    }

}
