package lk.ijse.cruddemo.dao;

import lk.ijse.cruddemo.Entity.Instructor;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);

}
