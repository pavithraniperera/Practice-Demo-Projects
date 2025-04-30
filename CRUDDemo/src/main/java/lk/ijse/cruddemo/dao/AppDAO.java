package lk.ijse.cruddemo.dao;

import lk.ijse.cruddemo.Entity.Instructor;
import lk.ijse.cruddemo.Entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);


}
