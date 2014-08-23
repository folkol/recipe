package recipe;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class RecipeService
{
    @PersistenceContext(unitName = "ReceptDS")
    EntityManager em;

    public int create(Recipe recipe) {
        em.persist(recipe);
        return recipe.id;
    }

    public Recipe retrieve(int id) {
        return em.find(Recipe.class, id);
    }

    public List<Recipe> findAll() {
        TypedQuery<Recipe> q = em.createQuery("select t from Recipe t", Recipe.class);
        return q.getResultList();
    }

    public void delete(int id) {
        Recipe recipe = em.find(Recipe.class, id);
        em.remove(recipe);
    }

    public void update(Recipe updatedRecipe, int id) {
        Recipe recipe = em.find(Recipe.class, id);
        recipe.title = updatedRecipe.title;
        recipe.description = updatedRecipe.description;
    }

}
