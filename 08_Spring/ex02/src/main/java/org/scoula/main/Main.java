package org.scoula.main;

import org.scoula.beans.Parrot;
import org.scoula.beans.Person;
import org.scoula.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        //Spring context(==Spring Contaner) 객체 얻어오기
        //-> Bean 으로 Parrot , Person 등록


        Person person = context.getBean(Person.class);
        System.out.println(person);


        Parrot parrot= context.getBean(Parrot.class);
        System.out.println(parrot);

        System.out.println("Person's name:"+person.getName());
        System.out.println("Parrot's name:"+parrot.getName());

        System.out.println("Person's parrot:"+person.getName());

    }
}
