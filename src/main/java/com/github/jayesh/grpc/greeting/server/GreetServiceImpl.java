package com.github.jayesh.grpc.greeting.server;

import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.greet.GreetingRequest;
import com.proto.greet.GreetingResponse;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {

        //extract field
        Greeting greeting =request.getGreeting();
        String firstName = greeting.getFirstName();
        String lastName = greeting.getLastName();

        //create the response
        String result = "my first name " + firstName + "\n" + " last name is " +  lastName;

        GreetingResponse response = GreetingResponse.newBuilder().setResult(result).build();


        //send the response
        responseObserver.onNext(response);
        //super.greet(request, responseObserver);

        responseObserver.onCompleted();


    }

}
