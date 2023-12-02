package com.mk.Green.Activity.Adapters;
import java.util.Observable;
import java.util.Observer;

public class StringObserver implements Observer {

    private String mString;

    public StringObserver(String string) {
        mString = string;
    }

    public void setString(String string) {
        mString = string;
      //  setChanged();
        //notifyObservers();
    }

    public String getString() {
        return mString;
    }

    @Override
    public void update(Observable observable, Object arg) {
      //  if (observable instanceof StringObserver) {
      //      StringObserver stringObserver = (StringObserver) observable;
        //    System.out.println("String has been updated to: " + stringObserver.getString());
        //}
    }
}
