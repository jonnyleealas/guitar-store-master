package controllers;

import com.google.inject.Inject;
import models.Guitar;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import models.*;
import repositories.GuitarRepository;

public class GuitarController extends Controller {

    @Inject
    public GuitarRepository guitarRepository;

    public Result findAll() {
        return ok(Json.toJson(guitarRepository.findAll())).as("application/json");
    }

    public Result findById(int id) {
        return ok(Json.toJson(guitarRepository.findById(id))).as("application/json");
    }

    public Result create() {
        Guitar guitarRequest = Json.fromJson(request().body().asJson(), Guitar.class);

        guitarRepository.add(guitarRequest);

        return ok(Json.toJson(guitarRequest)).as("application/json");
    }

    public Result update(int id) {
        Guitar guitarRequest = Json.fromJson(request().body().asJson(), Guitar.class);

        guitarRequest.setId(id);

        guitarRepository.update(guitarRequest);

        return ok(Json.toJson(guitarRequest)).as("application/json");
    }

    public Result delete(int id) {
        guitarRepository.delete(id);
        return ok("{}").as("application/json");
    }
}