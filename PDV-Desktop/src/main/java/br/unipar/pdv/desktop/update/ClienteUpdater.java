/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pdv.desktop.update;

import br.unipar.pdv.desktop.model.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Beatr
 */
public class ClienteUpdater {
    
    private List<Cliente> clientes;

    // Scheduler para atualizar o CEP a cada 5 minutos
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    // Lock para garantir que apenas uma thread por vez
    private final ReentrantLock lock = new ReentrantLock();

    public ClienteUpdater(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    // Método para iniciar a atualização
    public CompletableFuture<List<Cliente>> startUpdating() {
        CompletableFuture<List<Cliente>> future = new CompletableFuture<>();

        final Runnable updater = () -> {
            try {
                lock.lock();
                try {
                    clientes.clear();
//                    clientes.add(findCliente());

                    future.complete(clientes);
                } finally {
                    lock.unlock();
                }
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        };
        scheduler.schedule(updater, 5, TimeUnit.SECONDS);
        return future;
    }
    
    // Método para parar a atualização
    public void stopUpdating(int timeout) {
        scheduler.shutdown();

        try {
            if(!scheduler.awaitTermination(timeout, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

//    private Cliente findCliente(int id) throws IOException {
////         URL url = new
////                URL("http://localhost:8080/cliente/" + id);
////
////        BufferedReader in = new BufferedReader(
////                new InputStreamReader(url.openStream()));
////
////        String inputLine;
////        String result = "";
////        while ((inputLine = in.readLine()) != null) {
////            result += inputLine;
////        }
////        in.close();
////
////        return Cliente.unmarshalListFromJson(result.toString());
//    }
    
}
