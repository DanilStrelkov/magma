package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.ClientRequestDTO;
import org.example.model.dto.response.ClientResponceDTO;
import org.example.model.enumerated.status.ClientStatus;
import org.example.security.AuthService;
import org.example.security.JwtAuthentication;
import org.example.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    private final AuthService authService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("hello/user")
    public ResponseEntity<String> helloUser() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("hello/admin")
    public ResponseEntity<String> helloAdmin() {
        final JwtAuthentication authInfo = authService.getAuthInfo();
        return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
    }
    @PostMapping
    public ResponseEntity<ClientResponceDTO> create(@RequestBody ClientRequestDTO dto){
        return new ResponseEntity<>(clientService.create(dto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ClientResponceDTO>> readAll(){
        return new ResponseEntity<>(clientService.readAll(),HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponceDTO> readByid(@PathVariable Long id){
        return new ResponseEntity<>(clientService.readById(id),HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<ClientResponceDTO> update(@RequestBody ClientRequestDTO clientRequestDTO){
        return new ResponseEntity<>(clientService.update(clientRequestDTO),HttpStatus.OK);
    }
    @PutMapping("/{id}/{clientStatus}")
    public HttpStatus updateStatus(@PathVariable Long id, @PathVariable ClientStatus clientStatus){
        return clientService.updateStatus(id,clientStatus);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        return clientService.delete(id);
    }
}
