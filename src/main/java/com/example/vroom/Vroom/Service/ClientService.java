package com.example.vroom.Vroom.Service;

import com.example.vroom.Vroom.Model.Client;
import com.example.vroom.Vroom.Model.LoginRequest;
import com.example.vroom.Vroom.Repository.ClientRepository;
import com.example.vroom.Vroom.config.jwt.JwtTokenResolver;
import com.example.vroom.Vroom.dto.UserDTO;
import com.example.vroom.Vroom.dto.UserRequestResponse;
import com.example.vroom.Vroom.exceptions.BadRequestException;
import com.example.vroom.Vroom.exceptions.NotFoundException;
import com.example.vroom.Vroom.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenResolver jwtTokenResolver;
    public List<UserRequestResponse> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(client -> modelMapper.map(client, UserRequestResponse.class)).toList();
    }

    public UserRequestResponse getClientById(Long id) throws NotFoundException {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new NotFoundException("Client not found");
        }
        return modelMapper.map(client, UserRequestResponse.class);
    }

    public UserDTO addClient(UserDTO user) {
        user.setPassword(Utils.hashWithKey(user.getPassword()));
        Client clientToAdd = modelMapper.map(user, Client.class);
        Client clientAdded = clientRepository.save(clientToAdd);
        return modelMapper.map(clientAdded, UserDTO.class);
    }

    public UserRequestResponse updateClient(UserRequestResponse client, Long id) throws NotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new NotFoundException("Client not found");
        }
        Client clientToUpdate = clientOptional.get();
        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());
        clientToUpdate.setEmail(client.getEmail());
        return modelMapper.map(clientRepository.save(clientToUpdate), UserRequestResponse.class);
    }

    public void deleteClient(Long id) throws NotFoundException {
        clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        clientRepository.deleteById(id);
    }

    public UserRequestResponse getClientByEmail(String email) throws NotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        return modelMapper.map(client, UserRequestResponse.class);
    }

    // login si de facut token
    public String login(LoginRequest user) throws NotFoundException, BadRequestException {
        Client client = clientRepository.findByEmail(user.getEmail());
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        if (client.getPassword().equals(Utils.hashWithKey(user.getPassword()))) {
            return jwtTokenResolver.generateJwtToken(client.getEmail(), client.getRole().name());
        }
        throw new BadRequestException("Invalid credentials");
    }

}
