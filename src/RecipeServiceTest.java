import static org.mockito.Mockito.*;

import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RecipeServiceTest {

    private RecipeService service;
    @Mock EntityManager em;
    @Mock TypedQuery<Recipe> tq;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new RecipeService();
        service.em = em;
    }

    @Test
    public void shouldStoreRecipe() {
        Recipe recipe = new Recipe();

        service.add(recipe);

        verify(em, times(1)).persist(recipe);
    }

    @Test
    public void shouldRetrieveObject() {
        int i = new Random().nextInt();

        service.retrieve(i);

        verify(em, times(1)).find(Recipe.class, i);
    }

    @Test
    public void shouldRetrieveAll() {
        when(em.createQuery("select t from Recipe t", Recipe.class)).thenReturn(tq);

        service.findAll();

        verify(tq, times(1)).getResultList();
    }

    @Test
    public void shouldDeleteRecipe() {
        int i = new Random().nextInt();
        Recipe recipe = new Recipe();
        when(em.find(Recipe.class, i)).thenReturn(recipe);

        service.delete(i);

        verify(em, times(1)).find(Recipe.class, i);
        verify(em, times(1)).remove(recipe);
    }

    @Test
    public void shouldUpdateRecipe() {
        Recipe oldRecipe = new Recipe();
        int i = new Random().nextInt();
        Recipe newRecipe = new Recipe();
        newRecipe.title = UUID.randomUUID().toString();
        newRecipe.description = UUID.randomUUID().toString();

        when(em.find(Recipe.class, i)).thenReturn(oldRecipe);

        service.update(newRecipe, i);

        Assert.assertEquals("Title not updated", oldRecipe.title, newRecipe.title);
        Assert.assertEquals("Description not updated", oldRecipe.description, newRecipe.description);
    }

}
