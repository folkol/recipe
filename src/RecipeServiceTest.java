import static org.mockito.Mockito.*;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

}
