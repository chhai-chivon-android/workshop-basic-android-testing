package workshop.testing.data.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Recipe {
    private static final String ID_PREFIX = "id=";
    private static final String TITLE_PREFIX = "title=";
    private static final String IMAGE_PREFIX = "img=";

    public final String id;
    public final String title;
    public final String image;
    public final String description;

    private Recipe(String id, String title, String img, String description) {
        this.id = id;
        this.title = title;
        this.image = img;
        this.description = description;
    }

    public static Recipe readFromStream(InputStream stream) {
        String id = null;
        String title = null;
        String img = null;
        StringBuilder descBuilder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.startsWith(ID_PREFIX)) {
                    id = line.substring(ID_PREFIX.length());
                    continue;
                }
                if (line.startsWith(IMAGE_PREFIX)) {
                    img = line.substring(IMAGE_PREFIX.length());
                    continue;
                }
                if (line.startsWith(TITLE_PREFIX)) {
                    title = line.substring(TITLE_PREFIX.length());
                    continue;
                }
                if (descBuilder.length() > 0) {
                    descBuilder.append("\n");
                }
                descBuilder.append(line);
            }
        } catch (IOException e) {
            return null;
        }

        return new Recipe(id, title, img, descBuilder.toString());
    }

}