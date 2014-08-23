package recipe;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationPath("/ws")
@Path("")
public class RecipeResource extends Application
{

    @Inject
    RecipeService recipeService;

    @GET
    public List<Recipe> findAll() {
        return recipeService.findAll();
    }

    @GET
    @Path("{id}")
    public Recipe retrieve(@PathParam("id") int id) {
        return recipeService.retrieve(id);
    }

    @PUT
    @Path("{id}")
    public Response update(Recipe recipe, @PathParam("id") int id) {
        recipeService.update(recipe, id);
        return Response.ok().build();
    }

    @POST
    public int create(Recipe recipe) {
        return recipeService.create(recipe);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        recipeService.delete(id);
        return Response.noContent().build();
    }

}
