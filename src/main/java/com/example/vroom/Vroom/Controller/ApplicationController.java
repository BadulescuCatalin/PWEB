package com.example.vroom.Vroom.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @GetMapping("/admin/data")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdminData() {
        return "Data for Admins";
    }

    @GetMapping("/client/data")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public String getClientData() {
        return "Data for Clients";
    }

    @GetMapping("/common/data")
    public String getCommonData() {
        return "Data for Admins and Clients";
    }
}

