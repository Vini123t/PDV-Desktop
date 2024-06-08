/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pdv.desktop.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Beatr
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente implements Serializable {
    
    private long id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
  
    public static List<Cliente> unmarshalFromJson(String json)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Cliente> clienteList = mapper.readValue(json,
                new TypeReference<List<Cliente>>(){});
        return clienteList;
    }

    public static String marshalToJson(Cliente cliente)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cliente);
    }
    
    public static Cliente unmarshalFromJsonFind(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Cliente.class);
    }
    
}
