package rw.gakbank.corebanking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getAccount(@PathVariable String id) {
        System.out.println("reached >>>>>>");
        return new ResponseEntity<>("Account" + id, HttpStatus.OK);
    }
}
