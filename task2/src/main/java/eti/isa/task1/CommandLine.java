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
    Scanner sc=new Scanner(System.in);

    @Autowired
    public CommandLine(EngineService engineService, ProducerService producerService) {
        this.engineService = engineService;
        this.producerService = producerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Producer producer = new Producer("Audi",55080,1909);
        producerService.create(producer);
        Producer producer2 = new Producer("Ferrari",68875,1947);
        producerService.create(producer2);

        //Engine engine = new Engine(Long.valueOf(0),"TFSI",1999,2010,producer);
        Engine engine = new Engine(Long.valueOf(0),"1.6 8V",1595,2008,producer);
        engineService.create(engine);
        Engine engine2 = new Engine(Long.valueOf(0),"1.8 Turbo",1798,1995,producer);
        engineService.create(engine2);
        Engine engine3 = new Engine(Long.valueOf(0),"V12 DOHC",5998,2002,producer2);
        engineService.create(engine3);

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
                    sc.close();
                    System.exit(0);
            }
        }
    }

    public void addProducer() {
        //Scanner sc=new Scanner(System.in);
        Producer producer = new Producer();
        System.out.println("Input producer name:");
        producer.setName(sc.next());
        System.out.println("Input nip:");
        producer.setNip(sc.nextInt());
        System.out.println("Input producer year:");
        producer.setYearz(sc.nextInt());
        producerService.create(producer);
        
    }

    public void addEngine() {
        //Scanner sc=new Scanner(System.in);
        Engine engine = new Engine();
        System.out.println("Input engine name:");
        engine.setName(sc.next());
        System.out.println("Input engine capacity:");
        engine.setCapacity(sc.nextInt());
        System.out.println("Input engine year:");
        engine.setYearz(sc.nextInt());

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
        //Scanner sc=new Scanner(System.in);
        System.out.println("Input producer name to delete:");
        String producerName = sc.next();
        if (producerService.find(producerName).isPresent())
        producerService.delete(producerService.find(producerName).get());
        else
            System.err.println("Brand doesn't  exist");
    }

    public void deleteEngine() {
        //Scanner sc=new Scanner(System.in);
        System.out.println("Input engine id to delete:");
        Long id = sc.nextLong();
        engineService.delete(id);
    }
}
