package model;

public enum StateType {
    Q, //normal state
    R, //back state
    T, //end state
    E; //error state

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
