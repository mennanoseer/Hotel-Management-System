public class Receptionist extends Employee {
    private String shift;

    public Receptionist(String name, String id, String shift) {
        super(name, id);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

}
