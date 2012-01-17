package sample.pv;

import java.awt.Color;

/**
 * @author eguchi
 */
public interface IView{

    void setHeight( String height );

    void setWeight( String weight );

    void setBmi( String bmi );

    void setBmiBackground( Color bg );
}
