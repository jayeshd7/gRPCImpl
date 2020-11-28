package com.github.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.greet.GreetingRequest;
import com.proto.greet.GreetingResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("hello client !!");
        //here client could be any one (not necessary a java)
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext() //for only localhost not for production
                .build();

        System.out.println("creating stub");

        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(managedChannel);

        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(managedChannel);

        //creating greet service client (blocking synchronous)
        GreetServiceGrpc.GreetServiceBlockingStub greetingClient = GreetServiceGrpc.newBlockingStub(managedChannel);

        //creating protocol buffer
        Greeting greeting = Greeting.newBuilder().setFirstName("Bhavi").setLastName("dalal").build();

        //do the same for greeting Request
        GreetingRequest greetingRequest = GreetingRequest.newBuilder().setGreeting(greeting).build() ;

        //call the RPC and get the response
        GreetingResponse greetingResponse = greetingClient.greet(greetingRequest);

        System.out.println(greetingResponse.getResult());

      //do something
        System.out.println("shutting channel");
        managedChannel.shutdown();


    }
}
