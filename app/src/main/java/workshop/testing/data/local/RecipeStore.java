package workshop.testing.data.local;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.testing.data.model.Recipe;

public class RecipeStore {
    public final List<Recipe> recipes = new ArrayList<>();
    private final Map<String, Recipe> map = new HashMap<>();

    public RecipeStore(Context context, String directory) {
        File file = null;
        InputStream stream = null;
        try {
            String[] fileNames = context.getAssets().list(directory);
            for (String fileName : fileNames) {
                try {
                    file = new File(directory, fileName);
                    stream = context.getAssets().open(file.getPath());
                    Recipe recipe = Recipe.readFromStream(stream);
                    recipes.add(recipe);
                    map.put(recipe.id, recipe);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Recipe getRecipe(String id) {
        return map.get(id);
    }
}
