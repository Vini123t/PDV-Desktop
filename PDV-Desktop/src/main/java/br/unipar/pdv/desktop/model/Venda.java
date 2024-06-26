/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pdv.desktop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

public class Venda implements Serializable{
    
    private long id;
    private String observacao;
    private Date data;
    private double total;
    private Cliente cliente;
    private List<ItemVenda> itens = new ArrayList<>();
    
//    public static List<Venda> unmarshalFromJson(String json)
//            throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        List<Venda> vendaList = mapper.readValue(json,
//                new TypeReference<List<Venda>>(){});
//        return vendaList;
//    }
    
    public static Venda unmarshalFromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Venda.class);
    }

    public static String marshalToJson(Venda venda)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(venda);
    }
     
}
