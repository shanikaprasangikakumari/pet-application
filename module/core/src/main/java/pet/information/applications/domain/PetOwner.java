/*
 * Copyright (c) Copyright 2010-2018 hSenid Mobile Solutions (Pvt) Limited.
 *               All Rights Reserved.
 *
 *               These materials are unpublished, proprietary, confidential source code of
 *               hSenid Mobile Solutions (Pvt) Limited and constitute a TRADE SECRET
 *               of hSenid Mobile Solutions (Pvt) Limited.
 *
 *               hSenid Mobile Solutions (Pvt) Limited retains all title to and intellectual
 *               property rights in these materials.
 */

package pet.information.applications.domain;

public class PetOwner  {

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", id='" + age + '\'' +
                '}';
    }
}
