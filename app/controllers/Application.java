package controllers;

import com.google.inject.Inject;
import model.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import services.Manager;
import views.html.*;

import static play.data.Form.form;

public class Application extends Controller
{
    @Inject
    private Manager manager;

    public Result index()
	{
        return ok( index2.render(form(User.class)) );
    }

    public Result register()
    {
        Form<User> userForm = form(User.class).bindFromRequest();

        try {
            manager.set(userForm.get());
        } catch (Exception e) {
            //TODO
        }

        return ok(sent2.render());
    }

    public Result sent()
    {
        return ok(sent2.render());
    }
}
