package br.com.sobre.sobremesa.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sobremesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Date deleted;
    String nome;
    String descricao;
    String preco;
    String image_uri;
    Boolean vegana;
    Boolean semgluten;
    

    
}
