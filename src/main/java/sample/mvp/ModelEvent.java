package sample.mvp;

import java.util.EventObject;

/**
 * @author eguchi
 */
public class ModelEvent extends EventObject{

    public ModelEvent( Object source ){
        super( source );
    }
}
