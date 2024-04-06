package common;

import java.util.List;

public class CoffeeDataDTO {
    public final int id;
    public final String title;
    public final String description;
    public final List<String> ingredients;
    public final String image;

    public CoffeeDataDTO(int id, String title, String description, List<String> ingredients, String image){
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.image = image;
    }
}
