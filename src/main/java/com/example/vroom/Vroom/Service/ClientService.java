package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Admin;
import com.example.vroom.Vroom.Model.Client;
import com.example.vroom.Vroom.Model.LoginRequest;
import com.example.vroom.Vroom.Repository.ClientRepository;
import com.example.vroom.Vroom.config.jwt.JwtTokenResolver;
import com.example.vroom.Vroom.dto.UserDTO;
import com.example.vroom.Vroom.dto.UserDetailsDTO;
import com.example.vroom.Vroom.exceptions.BadRequestException;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import com.example.vroom.Vroom.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenResolver jwtTokenResolver;
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public UserDTO addClient(UserDTO user) {
        user.setPassword(Utils.hashWithKey(user.getPassword()));
        Client clientToAdd = modelMapper.map(user, Client.class);
        Client clientAdded = clientRepository.save(clientToAdd);
        return modelMapper.map(clientAdded, UserDTO.class);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<Client> getClientByLastNameAndFirstName(String lastName, String firstName) {
        return clientRepository.findByLastNameAndFirstName(lastName, firstName);
    }
    // login si de facut token
    public String login(LoginRequest user) throws NotFoundException, BadRequestException {
        Client client = clientRepository.findByEmail(user.getEmail());
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        if (client.getPassword().equals(Utils.hashWithKey(user.getPassword()))) {
            String token = jwtTokenResolver.generateJwtToken(client.getEmail(), client.getRole().name());
        }
        throw new BadRequestException("Invalid credentials");
    }

}
