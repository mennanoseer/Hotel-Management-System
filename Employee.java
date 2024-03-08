public class Employee implements User {
    private String name;
    private String id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Implemented method from User interface
    @Override
    public void displayDetails() {
        System.out.println("Employee: " + name + " (ID: " + id + ")");
    }
}
