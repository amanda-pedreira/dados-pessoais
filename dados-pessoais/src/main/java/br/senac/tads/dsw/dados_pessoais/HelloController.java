package br.senac.tads.dsw.dados_pessoais;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mensagem hello() {
        return new Mensagem("Amanda Luana Pedreira", "Olá, mundo! Meu primeiro endpoint Spring Boot.");
    }


    @GetMapping(value = "/hello-manual", produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloManual() throws JsonProcessingException {
        Mensagem mensagem = new Mensagem("Amanda Luana Pedreira", "JSON gerado manualmente com ObjectMapper. ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(mensagem);
    }
}



