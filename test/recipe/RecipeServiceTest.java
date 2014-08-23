package recipe;

import static java.util.UUID.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RecipeServiceTest {

    @Mock EntityManager em;
    @Mock TypedQuery<Recipe> tq;

    private RecipeService target;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        target = new RecipeService();
        target.em = em;
    }

    @Test
    public void shouldStoreRecipe() {
        Recipe recipe = new Recipe();

        target.create(recipe);

        verify(em, times(1)).persist(recipe);
    }

    @Test
    public void shouldRetrieveObject() {
        int i = new Random().nextInt();

        target.retrieve(i);

        verify(em, times(1)).find(Recipe.class, i);
    }

    @Test
    public void shouldRetrieveAll() {
        when(em.createQuery("select t from Recipe t", Recipe.class)).thenReturn(tq);

        target.findAll();

        verify(tq, times(1)).getResultList();
    }

    @Test
    public void shouldDeleteRecipe() {
        int i = new Random().nextInt();
        Recipe recipe = new Recipe();
        when(em.find(Recipe.class, i)).thenReturn(recipe);

        target.delete(i);

        verify(em, times(1)).remove(recipe);
    }

    @Test
    public void shouldUpdateRecipe() {
        Recipe oldRecipe = new Recipe(), newRecipe = new Recipe();
        newRecipe.title = randomUUID().toString();
        newRecipe.description = randomUUID().toString();
        int i = new Random().nextInt();
        when(em.find(Recipe.class, i)).thenReturn(oldRecipe);

        target.update(newRecipe, i);

        Assert.assertEquals("Title not updated", oldRecipe.title, newRecipe.title);
        Assert.assertEquals("Description not updated", oldRecipe.description, newRecipe.description);
    }

}
