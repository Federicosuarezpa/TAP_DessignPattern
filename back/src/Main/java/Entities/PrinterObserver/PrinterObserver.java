package Entities.PrinterObserver;

import Entities.ActorListener.ActorListener;

public class PrinterObserver implements ActorListener {

    @Override
    public void update(int state) {
        System.out.println( "Nueva prueba" + state );
    }
}
