package org.model;

import org.di.annotations.CInject;

public class School {

    @CInject
    private User user;

    public School() {

    }

    //    @CInject
    public School(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "School{" +
            "user=" + user +
            '}';
    }
}
