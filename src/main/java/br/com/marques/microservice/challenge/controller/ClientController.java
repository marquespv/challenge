package br.com.marques.microservice.challenge.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.marques.microservice.challenge.model.Client;
import br.com.marques.microservice.challenge.repository.ClientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientRepository ClientRepository;

    @RequestMapping(method= RequestMethod.GET)
    public List<Client> list() {
        return ClientRepository.findAll();
    }
      
    @GetMapping("/{id}")
    public Client clientById(@PathVariable("Id") Long Id){
        return ClientRepository.findById(Id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client){
        return ClientRepository.save(client);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        Optional<Client> clientData = ClientRepository.findById(id);

        if (clientData.isPresent()) {
            Client _client = clientData.get();
            _client.setNome(client.getNome());
            _client.setCpf(client.getCpf());
            _client.setEndereco(client.getEndereco());
            return new ResponseEntity<>(ClientRepository.save(_client), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id) {
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}