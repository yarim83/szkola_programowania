package pl.coderslab.model;

public class UserGroup {

    private int id;
    private String name;

    public UserGroup(){}

    public UserGroup( String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n" + "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
