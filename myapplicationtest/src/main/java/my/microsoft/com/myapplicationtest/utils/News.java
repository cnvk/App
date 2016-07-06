package my.microsoft.com.myapplicationtest.utils;

/**
 * Created by my on 2016/6/24.
 */
public class News {
    private String id;
    private String title;
    private String shorttitle;
    private String litpic;
    private String description;

    public News(String id, String title, String shorttitle, String litpic, String description) {
        this.id = id;
        this.title = title;
        this.shorttitle = shorttitle;
        this.litpic = litpic;
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", shorttitle='" + shorttitle + '\'' +
                ", litpic='" + litpic + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
