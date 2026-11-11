package org.example.student;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Map;

@Service
@Transactional
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository){this.repository=repository;}

    @Transactional(readOnly=true)
    public Page<Student> list(int page, int size, String search, StudentStatus status, String course, String sortBy, String direction) {
        if(page<0 || size<1 || size>100) throw new IllegalArgumentException("page must be nonnegative and size must be between 1 and 100");
        var allowed=java.util.Set.of("id","firstName","lastName","email","course","enrollmentDate","status");
        if(!allowed.contains(sortBy)) throw new IllegalArgumentException("Unsupported sortBy field");
        Sort sort=Sort.by("desc".equalsIgnoreCase(direction)?Sort.Direction.DESC:Sort.Direction.ASC,sortBy);
        Specification<Student> spec=(root,q,cb)->cb.conjunction();
        if(search!=null&&!search.isBlank()) { String term="%"+search.trim().toLowerCase()+"%"; spec=spec.and((r,q,cb)->cb.or(cb.like(cb.lower(r.get("firstName")),term),cb.like(cb.lower(r.get("lastName")),term),cb.like(cb.lower(r.get("email")),term),cb.like(cb.toString(r.get("id")),term))); }
        if(status!=null) spec=spec.and((r,q,cb)->cb.equal(r.get("status"),status));
        if(course!=null&&!course.isBlank()) spec=spec.and((r,q,cb)->cb.equal(cb.lower(r.get("course")),course.trim().toLowerCase()));
        return repository.findAll(spec,PageRequest.of(page,size,sort));
    }
    @Transactional(readOnly=true) public Student get(long id){return repository.findById(id).orElseThrow(()->new NotFoundException("Student not found"));}
    public Student create(StudentRequest request){ if(repository.existsByEmailIgnoreCase(request.email())) throw new DuplicateEmailException(); return repository.save(new Student(request)); }
    public Student update(long id,StudentRequest request){ Student student=get(id); if(repository.existsByEmailIgnoreCaseAndIdNot(request.email(),id)) throw new DuplicateEmailException(); student.update(request); return repository.save(student); }
    public void delete(long id){repository.delete(get(id));}
    @Transactional(readOnly=true) public Map<String,Object> statistics(){
        return Map.of("total",repository.count(),"active",repository.countByStatus(StudentStatus.ACTIVE),"inactive",repository.countByStatus(StudentStatus.INACTIVE),"recentlyAdded",repository.countByEnrollmentDateAfter(LocalDate.now().minusDays(30)),"recentStudents",repository.findAllByOrderByEnrollmentDateDesc(PageRequest.of(0,5)).getContent());
    }
}
