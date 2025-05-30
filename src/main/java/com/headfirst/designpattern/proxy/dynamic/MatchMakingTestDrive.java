package com.headfirst.designpattern.proxy.dynamic;

import java.lang.reflect.Proxy;

public class MatchMakingTestDrive {
    public void test(){
        Person person = new PersonImpl();
        person.setName("John Doe");
        person.setGender("Men");
        person.setInterests("Hiking, Reading");
        person.setGeekRating(5);

        Person ownerProxy = getOwnerProxy(person);
        System.out.println("Owner Proxy:");
        System.out.println("Name: " + ownerProxy.getName());
        ownerProxy.setInterests("Cooking, Traveling");
        try {
            ownerProxy.setGeekRating(10); // Should succeed
        } catch (Exception e) {
            System.out.println("Cannot set geek rating as owner.");
        }
        System.out.println("Interests: " + ownerProxy.getInterests());
        System.out.println("Geek Rating: " + ownerProxy.getGeekRating());

        Person nonOwnerProxy = getNonOwnerProxy(person);
        System.out.println("\nNon-Owner Proxy:");

        try {
            System.out.println("Name: " + nonOwnerProxy.getName());
            nonOwnerProxy.setInterests("Cooking, Traveling"); // Should succeed
            nonOwnerProxy.setGeekRating(10); // Should throw exception
        } catch (Exception e) {
            System.out.println("Cannot set geek rating as non-owner.");
        }
        System.out.println("Interests: " + nonOwnerProxy.getInterests());
        System.out.println("Geek Rating: " + nonOwnerProxy.getGeekRating());
    }

    Person getOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocatoinHandler(person)
        );
    }
    Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person)
        );
    }
}
