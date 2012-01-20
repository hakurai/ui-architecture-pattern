package sample.mvp;

import javax.swing.event.EventListenerList;

/**
 * @author eguchi
 */
public class Model{

    private EventListenerList listenerList = new EventListenerList();

    private String height = "";
    private String weight = "";
    private String bmi = "";


    public String getWeight(){
        return weight;
    }

    public String getHeight(){
        return height;
    }

    public String getBMI(){
        return bmi;
    }

    public void setWeight( String weight ){
        this.weight = weight;
        updateBMI();
        fireModelChanged();
    }


    public void setHeight( String height ){
        this.height = height;
        updateBMI();
        fireModelChanged();
    }

    private void updateBMI(){

        float height;
        float weight;

        try{
            height = Float.valueOf( this.height );
            weight = Float.valueOf( this.weight );
        } catch ( NumberFormatException e ){
            return;
        }

        if( height == 0f ){
            return;
        }

        float bmi = weight / ( height * height );

        this.bmi = Float.toString( bmi );
    }


    protected void fireModelChanged(){
        ModelListener[] listeners = listenerList.getListeners( ModelListener.class );
        ModelEvent event = new ModelEvent( this );
        for( ModelListener l : listeners ){
            l.modelChanged( event );
        }
    }


    public void addModelListener( ModelListener listener ){
        listenerList.add( ModelListener.class, listener );
    }

    public void removeModelListener( ModelListener listener ){
        listenerList.remove( ModelListener.class, listener );
    }
}
