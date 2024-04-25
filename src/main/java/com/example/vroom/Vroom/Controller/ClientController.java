package com.example.vroom.Vroom.Controller;

import com.example.vroom.Vroom.Service.ClientService;
import com.example.vroom.Vroom.dto.UserRequest;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @PutMapping("/update/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody UserRequest client) throws NotFoundException {
        clientService.updateClient(client, id);
    }

}
