/**

package br.com.Anthoniessaroto.todolist.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

//@Controller Nao ira ser usado pois nao serve para api
@RestController // Usamos para fazer uma API e queremos retornar uma api

@RequestMapping("/primeiraRota")
// http://localhost:8000/primeiraRota ------
public class primeiraController 
{

    //Metodos de acesso HTTP
    /GET - Buscar uma dado
    //POST - Adicionar um dado
    //PUT - Alterar uma dado
    //DELETE - Remover um dado
    //PATCH - Alterar apenas uma apenas parte do dado

    // Metodo (funcionalidade) de uma classe
    @GetMapping("/")
    public String primeiraMensagem ()
    {
        return "Funcionou";
    }
}   

**/