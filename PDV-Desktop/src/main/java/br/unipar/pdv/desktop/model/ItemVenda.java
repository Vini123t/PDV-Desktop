/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pdv.desktop.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class ItemVenda {
    
    private long id;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    
    public static List<ItemVenda> unmarshalFromJson(String json)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<ItemVenda> itemVendaList = mapper.readValue(json,
                new TypeReference<List<ItemVenda>>(){});
        return itemVendaList;
    }

    public static String marshalToJson(ItemVenda itemVenda)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(itemVenda);
    }
}
