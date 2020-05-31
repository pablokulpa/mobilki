package com.hellohasan.sqlite_multiple_three_tables_crud.model;

public class TakenSubject extends Subject{

    private boolean isTaken;

    public TakenSubject(int id, String name, boolean isTaken) {
        super(id, name);
        this.isTaken = isTaken;
    }

    public boolean isTaken() {
        return isTaken;
    }
}
