package com.example.devoir2;

import com.example.devoir2.entities.Patient;
import com.example.devoir2.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Devoir2Application {

    public static void main(String[] args) {

        SpringApplication.run(Devoir2Application.class, args);
    }
    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
                Patient p1 = new Patient();
                p1.setNom("Koutit");
                p1.setDateNaissance(new Date());
                p1.setMalade(true);
                p1.setScore(100);

                Patient p2 = new Patient(null, "Saad", new Date(), false, 200);

                Patient p3 = Patient.builder()
                        .nom("Oussama")
                        .score(600)
                        .build();

                patientRepository.save(p1);
                patientRepository.save(p2);
                patientRepository.save(p3);

                List<Patient> patients = patientRepository.findAll();
                patients.forEach(p -> {
                    System.out.println(p.toString());
                });
                Patient patient = patientRepository.findById(Long.valueOf(2)).get();
                System.out.println("1***************");
                System.out.println(patient.getId());
                System.out.println(patient.getNom());
                System.out.println(patient.getDateNaissance());

                patientRepository.deleteById(Long.valueOf(3));
                List<Patient> patients1 = patientRepository.findAll();
                patients1.forEach(p -> {
                    System.out.println(p);
                });

                System.out.println("2***************");
                List<Patient> patientList = patientRepository.findByNomContains("Saa");
                patientList.forEach(p -> {
                System.out.println(p);
                } );
                System.out.println("3***************");
                List<Patient> patientList2 = patientRepository.search("%s%");
                patientList2.forEach(p -> {
                    System.out.println(p);
                } );
        };
    }
}

