package controllers;

import dao.ElementDao;
import models.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.SQLException;

public class ElementController extends Controller {

    private static final Element defaultElement = new Element("Search again", -1, "-", "-");

    private ElementDao dao;

    @Inject
    public ElementController(ElementDao dao) {
        this.dao = dao;
    }

    public Result element() throws SQLException {
        String searchTerm = request().getQueryString("element");

        JSONArray jsonArray = dao.select(searchTerm);

        Element element = (jsonArray.length()>0) ? mapToElement(jsonArray.getJSONObject(0)) : defaultElement;

        return ok(Json.toJson(element));
    }

    @Transactional
    public Result template() {
        return ok(views.html.template.render());
    }

    private Element mapToElement(JSONObject theElementSet) {
        String element = theElementSet.getString("element");
        int atomicNumber = theElementSet.getInt("atomic_number");
        String symbol = theElementSet.getString("symbol");
        String metalGroup = theElementSet.getString("metal_group");
        return new Element(element, atomicNumber, symbol, metalGroup);
    }

}
