package se.thinkcode;

import io.javalin.Javalin;

public class Library {

    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .start(7070);
        routes(app);
    }

    public static void routes(Javalin app) {
        app.get("/hello/{name}", ctx -> {
            ctx.result("Hello: " + ctx.pathParam("name"));
        });
        app.get("/path/*", ctx -> { // will match anything starting with /path/
            ctx.result("You are here because " + ctx.path() + " matches " + ctx.matchedPath());
        });
    }

}
