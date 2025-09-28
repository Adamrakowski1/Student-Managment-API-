package com.example.demo;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Objects;
import java.util.List;
import java.util.Optional;



@Service
public class studentService {

    private final studentRepository studentRepository;
    
    public studentService(studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<student> getStudents() {
		return studentRepository.findAll();
	}

    public void addNewStudent(student student) {
        Optional<student> studentOptional = studentRepository.
        findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with Id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        student student = studentRepository.findById(studentId).orElseThrow(() -> 
        new IllegalStateException("Student with id " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }


    }
}
