package my.id.elianiva.core.models;

public class Rule {
    public static final int MAX_DESCRIPTION_LENGTH = 60;
    public static final int ID_LENGTH = 4;
    private String id;
    private String description;
    private int point;

    public Rule(String id, String description, int point) {
        this.id = id;
        this.description = description;
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void update(String newDescription, int newPoint) {
        if (!newDescription.trim().isEmpty()) {
            this.description = newDescription;
        }
        if (newPoint != -1) {
            this.point = newPoint;
        }
    }
}
