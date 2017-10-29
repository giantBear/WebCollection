package server;

import java.util.ArrayList;
import client.Observer;


/**
 * @author Yunong
 * @version 1.0
 * @time 01/05/2017
 */
public abstract class Subject {

    private ArrayList<Observer> ObserverList=new ArrayList<Observer>();

    public ArrayList<Observer> getObserverList()
    {
        return ObserverList;
    }

    public String register(Observer Observer)
    {
        ObserverList.add(Observer);
        return  "registered";
    }

    public void unregister(Observer Observer)
    {
        ObserverList.remove(Observer);
    }

    public String notifyObservers()
    {
        for (Observer Observer:ObserverList)
            Observer.update();
        return  "Observers notified";
    }
}
