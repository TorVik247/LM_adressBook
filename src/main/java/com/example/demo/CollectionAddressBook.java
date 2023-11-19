package com.example.demo;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAddressBook implements AddressBook{

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {

    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

    }

    public ObservableList <Person>getPersonList(){return personList;}

     public void print(){
        int number = 0;
        System.out.println();
        for (Person person: personList){
            number++;
            System.out.println(number+") ПІП: "+person.getPIP()+"; Телефон: "+person.getPhone());
        }
     }

     public void fillTestData(){
        personList.add(new Person("cvbg","23464"));
         personList.add(new Person("werfgh","67899"));
         personList.add(new Person("xcvb","98755"));

     }
}
