package balu.sbms.studentservice.service;

import balu.sbms.studentservice.entity.Student;
import balu.sbms.studentservice.feignclients.AddressFeignClient;
import balu.sbms.studentservice.repository.StudentRepository;
import balu.sbms.studentservice.request.StudentRequest;
import balu.sbms.studentservice.response.AddressResponse;
import balu.sbms.studentservice.response.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    AddressFeignClient addressFeignClient;

    public StudentResponse createStudentResponse(StudentRequest studentRequest){
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());

        student.setAddress(studentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddress()));
        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddress()));

        return studentResponse;
    }

    public StudentResponse getById (long id) {
        logger.info("Student Service - - Inside getById " + id);
        Student student = studentRepository.findById(id).get();

        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddress()));
        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddress()));
        return studentResponse;
    }

    public AddressResponse getAddressById(long addressId){
        Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
                .retrieve().bodyToMono(AddressResponse.class);

        return addressResponseMono.block();
    }
}
