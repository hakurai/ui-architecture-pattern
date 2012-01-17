package sample.mvc;

import javax.swing.event.EventListenerList;
import java.awt.Color;

/**
 * @author eguchi
 */
public class Model{

    private EventListenerList listenerList = new EventListenerList();

    private String height = "";
    private String weight = "";
    private String bmi = "";
    private Color bmiColor = Color.WHITE;

    public String getWeight(){
        return weight;
    }

    public String getHeight(){
        return height;
    }

    public String getBmi(){
        return bmi;
    }

    public Color getBmiColor(){
        return bmiColor;
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

        if( bmi < 18.5f ){
            bmiColor = Color.WHITE;
        } else if( bmi < 20.0f ){
            bmiColor = Color.YELLOW;
        } else if( bmi < 30 ){
            bmiColor = Color.ORANGE;
        } else{
            bmiColor = Color.RED;

        }
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
