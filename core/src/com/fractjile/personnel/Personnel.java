package com.fractjile.personnel;

public abstract class Personnel {

    public String firstName;
    public String middleName;
    public String lastName;

    public byte age;
    public String dateOfBirth; // Should be in format dd/mm/yyyy

    public String nationality;

    public String team;

    public Personnel(String firstName, String lastName, byte age, String dateOfBirth, String nationality, String team) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.team = team;

    }

    public Personnel(String firstName, String middleName, String lastName, byte age, String dateOfBirth, String nationality, String team) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.team = team;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public byte getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }
}
