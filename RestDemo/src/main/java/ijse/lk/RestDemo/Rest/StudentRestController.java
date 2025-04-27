package ijse.lk.RestDemo.Rest;

import ijse.lk.RestDemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //define @PostConstruct to load the student data only once
    @PostConstruct
    public void loadData(){
         theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));


    }

    @GetMapping("/student")
    public List<Student> getStudents(){


        return theStudents;
    }
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // simply access by index of the list
        return theStudents.get(studentId);

    }



}
