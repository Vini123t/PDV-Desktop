/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pdv.desktop.update;

import br.unipar.pdv.desktop.model.Produto;
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
public class ProdutoUpdater {
    
    private List<Produto> produtos;

    // Scheduler para atualizar o CEP a cada 5 minutos
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    // Lock para garantir que apenas uma thread por vez
    private final ReentrantLock lock = new ReentrantLock();

    public ProdutoUpdater(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getClientes() {
        return produtos;
    }
    
    // Método para iniciar a atualização
    public CompletableFuture<List<Produto>> startUpdating() {
        CompletableFuture<List<Produto>> future = new CompletableFuture<>();

        final Runnable updater = () -> {
            try {
                lock.lock();
                try {
                    produtos.clear();
                    for (int i = 0; i < produtos.size(); i++) {
                        produtos.add(findCliente(i));
                    }
                    future.complete(produtos);
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

    private Produto findCliente(int id) throws IOException {
         URL url = new
                URL("http://localhost:8080/produto/" + id);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }
        in.close();

        Produto produto = Produto.unmarshalFromJsonFind(result);
        
        return produto;
    }
    
    // Método para imprimir o cliente
    public void printString() {
        final Runnable print = new Runnable() {
            public void run() {
                for (Produto produto : produtos) {
                    System.out.println(produto.toString());
                }

                stopUpdating(1);
            }
        };
        scheduler.schedule(print, 10, TimeUnit.SECONDS);
    }
}
