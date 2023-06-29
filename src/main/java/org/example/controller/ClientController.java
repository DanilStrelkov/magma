package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.ClientDTO;
import org.example.model.entity.Client;
import org.example.model.enumerated.status.ClientStatus;
import org.example.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO dto){
        return new ResponseEntity<>(clientService.create(dto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Client>> readAll(){
        return new ResponseEntity<>(clientService.readAll(),HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> readByid(@PathVariable Long id){
        return new ResponseEntity<>(clientService.readById(id),HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<Client> update(@RequestBody Client client){
        return new ResponseEntity<>(clientService.update(client),HttpStatus.OK);
    }
    @PutMapping("/{id}/{clientStatus}")
    public ResponseEntity<Client> updateStatus(@PathVariable Long id,@PathVariable ClientStatus clientStatus){
        return new ResponseEntity<>(clientService.updateStatus(id,clientStatus),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        clientService.delete(id);
        return HttpStatus.OK;
    }
}
