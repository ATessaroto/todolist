package br.com.Anthoniessaroto.todolist.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class userController 
{   
    @Autowired //Gerencia ciclo de vida
    private IUserRepository userRepository;
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody userModel userModel)
    {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null)
        {
            // Mensagem de erro e Status Code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe!");
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()); //Criptografa senha

        userModel.setPassword(passwordHashred);

        var user_created = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(user_created);
    }
}
