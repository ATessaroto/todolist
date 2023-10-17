package br.com.Anthoniessaroto.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.cglib.core.Local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class taskModel 
{   
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime StartAt;
    private LocalDateTime EndAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime CreatedAT;

    public void setTitle(String title) throws Exception
    {
        if(title.length() > 50)
        {
            throw new Exception("O campo title deve conter no maximo 50 caractertes!!");
        }

        this.title = title;
    }

}
