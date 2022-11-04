package eti.isa.task1;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import eti.isa.task1.engines.entity.*;
import eti.isa.task1.engines.service.EngineService;
import eti.isa.task1.engines.service.ProducerService;

@Component
public class CommandLine implements CommandLineRunner {

    private EngineService engineService;
    private ProducerService producerService;

    @Autowired
    public CommandLine(EngineService engineService, ProducerService producerService) {
        this.engineService = engineService;
        this.producerService = producerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Producer producer = new Producer("Audi",123,1950);
        producerService.create(producer);

        Engine engine = new Engine(1,"TFSI",1999,2010,producer);
        engineService.create(engine);

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("0 - add producer 1 - add engine \n2 - list all producers 3 - list all engines \n4 - delete producer 5 - delete engine\n9 - close\n");
            switch(sc.nextInt()){
                case 0:
                    addProducer();
                    break;
                case 1:
                    addEngine();
                    break;
                case 2:
                    listProducers();
                    break;
                case 3:
                    listEngines();
                    break;
                case 4:
                    deleteProducer();
                    break;
                case 5:
                    deleteEngine();
                    break;
                case 9:
                    System.exit(0);
            }
        }
    }

    public void addProducer() {
        Scanner sc=new Scanner(System.in);
        Producer producer = new Producer();
        System.out.println("Input producer name:");
        producer.setName(sc.next());
        System.out.println("Input nip:");
        producer.setNip(sc.nextInt());
        System.out.println("Input producer year:");
        producer.setYear(sc.nextInt());
        producerService.create(producer);
        
    }

    public void addEngine() {
        Scanner sc=new Scanner(System.in);
        Engine engine = new Engine();
        System.out.println("Input engine id:");
        engine.setId(sc.nextInt());
        System.out.println("Input engine name:");
        engine.setName(sc.next());
        System.out.println("Input engine capacity:");
        engine.setCapacity(sc.nextInt());
        System.out.println("Input engine year:");
        engine.setYear(sc.nextInt());

        System.out.println("Input engine producer name:");
        String producerName = sc.next();

        if (producerService.find(producerName).isPresent()) {
            engine.setProducer(producerService.find(producerName).get());
            engineService.create(engine);
        }
        else
        {
            System.err.println("Producer doesn't exist");
        }
    }

    public void listProducers() {
        System.out.println(producerService.findAll());
    }

    public void listEngines() {
        System.out.println(engineService.findAll());
    }

    public void deleteProducer() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input producer name to delete:");
        String producerName = sc.next();
        if (producerService.find(producerName).isPresent())
        producerService.delete(producerService.find(producerName).get());
        else
            System.err.println("Brand doesn't  exist");
    }

    public void deleteEngine() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Input engine name to delete:");
        engineService.delete(sc.next());
    }
}
