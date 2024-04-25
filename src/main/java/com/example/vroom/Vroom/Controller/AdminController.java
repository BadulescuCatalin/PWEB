package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

//    @PostMapping("/add_admin")
//    public ResponseEntity<Admin> addCourse(@RequestBody Course course) {
//        Course addedCourse = courseService.addCourse(course);
//        if (addedCourse != null) {
//            return ResponseEntity
//                    .ok()
//                    .body(addedCourse);
//        }
//        return ResponseEntity
//                .badRequest()
//                .body(null);
//    }
}
