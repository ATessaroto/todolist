package br.com.Anthoniessaroto.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.Anthoniessaroto.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //Adicionar quando quer q o Spring gerencie
public class filterTaskAuth extends OncePerRequestFilter
{
    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException 
    {

        var serveletPath = request.getServletPath();

        if(serveletPath.startsWith("/tasks/"))
        {
            //Pegar autenticação
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            //Validar User
            var userAuth = this.userRepository.findByUsername(username);
            if (userAuth == null)
            {
                response.sendError(401);
            }
            else
            {
                //Validar Senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), userAuth.getPassword());
                if (passwordVerify.verified)
                {
                    //Rodar programa
                    request.setAttribute("idUser", userAuth.getId());
                    filterChain.doFilter(request, response);
                }
                else
                {
                    response.sendError(401);
                }
            }
        }
        else
        {
            filterChain.doFilter(request, response);
        }
    }   
}
