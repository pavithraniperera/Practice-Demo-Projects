package ijse.lk.RestDemo.Rest;

import ijse.lk.RestDemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(studentId>theStudents.size() && studentId<0){
            throw new StudentNotFoundException("Student Id Not Found - "+studentId);
        }
        //Happy path
        return theStudents.get(studentId);

    }

    // in here it handle only integer bad values
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamps(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    //add any another exception  handler... to catch any exception(catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimestamps(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
