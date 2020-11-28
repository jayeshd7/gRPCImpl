package com.github.jayesh.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) {
        System.out.println("hello server !!");

        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .build();
        try {
            server.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Received shut down request.");
                server.shutdown();
                System.out.println("server shut down successfully..");
            }));

            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }



    }



}
