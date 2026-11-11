package org.example.student;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service){this.service=service;}
    @GetMapping public Page<Student> list(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size,
            @RequestParam(required=false) String search,@RequestParam(required=false) StudentStatus status,@RequestParam(required=false) String course,
            @RequestParam(defaultValue="enrollmentDate") String sortBy,@RequestParam(defaultValue="desc") String sortDirection){
        return service.list(page,size,search,status,course,sortBy,sortDirection);
    }
    @GetMapping("/{id}") public Student get(@PathVariable long id){return service.get(id);}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student create(@Valid @RequestBody StudentRequest request){return service.create(request);}
    @PutMapping("/{id}") public Student update(@PathVariable long id,@Valid @RequestBody StudentRequest request){return service.update(id,request);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id){service.delete(id);}
    @GetMapping("/statistics") public Map<String,Object> statistics(){return service.statistics();}
}
