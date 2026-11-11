package org.example.student;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(name = "uk_students_email", columnNames = "email"))
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 80) private String firstName;
    @Column(name = "last_name", nullable = false, length = 80) private String lastName;
    @Column(nullable = false, length = 254) private String email;
    @Column(length = 40) private String phone;
    @Column(name = "date_of_birth") private LocalDate dateOfBirth;
    @Column(length = 30) private String gender;
    @Column(length = 500) private String address;
    @Column(nullable = false, length = 120) private String course;
    @Column(name = "enrollment_date", nullable = false) private LocalDate enrollmentDate;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 16) private StudentStatus status;

    protected Student() {}
    public Student(StudentRequest r) { update(r); }
    public void update(StudentRequest r) {
        firstName=r.firstName().trim(); lastName=r.lastName().trim(); email=r.email().trim().toLowerCase();
        phone=r.phone(); dateOfBirth=r.dateOfBirth(); gender=r.gender(); address=r.address();
        course=r.course().trim(); enrollmentDate=r.enrollmentDate(); status=r.status();
    }
    public Long getId(){return id;} public String getFirstName(){return firstName;} public String getLastName(){return lastName;}
    public String getEmail(){return email;} public String getPhone(){return phone;} public LocalDate getDateOfBirth(){return dateOfBirth;}
    public String getGender(){return gender;} public String getAddress(){return address;} public String getCourse(){return course;}
    public LocalDate getEnrollmentDate(){return enrollmentDate;} public StudentStatus getStatus(){return status;}
}
