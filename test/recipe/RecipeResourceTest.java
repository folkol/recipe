package recipe;

import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class RecipeResourceTest {

    @Mock RecipeService recipeService;
    private RecipeResource target;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        target = new RecipeResource();
        target.recipeService = recipeService;
    }

    @Test
    public void findAll() {
        target.findAll();

        verify(recipeService, times(1)).findAll();
    }

    @Test
    public void retrieve() {
        int id = new Random().nextInt();
        target.retrieve(id);

        verify(recipeService, times(1)).retrieve(id);
    }

    @Test
    public void update() {
        Recipe recipe = new Recipe();
        int id = new Random().nextInt();

        target.update(recipe , id);

        verify(recipeService, times(1)).update(recipe, id);
    }

    @Test
    public void create() {
        Recipe recipe = new Recipe();

        target.create(recipe);

        verify(recipeService, times(1)).create(recipe);
    }

    @Test
    public void delete() {
        int id = new Random().nextInt();

        target.delete(id);

        verify(recipeService, times(1)).delete(id);
    }

}
