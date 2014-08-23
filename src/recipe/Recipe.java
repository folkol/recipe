package recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Recipe {
    @Id
    @GeneratedValue
    public int id;
    public String title;
    public String description;
}
