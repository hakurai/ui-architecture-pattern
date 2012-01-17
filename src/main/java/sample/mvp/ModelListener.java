package sample.mvp;

import java.util.EventListener;

/**
 * @author eguchi
 */
public interface ModelListener extends EventListener{

    void modelChanged( ModelEvent e );
}
