import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {

    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String department;
    private int salary;
}