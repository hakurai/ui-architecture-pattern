package sample.pv;

/**
 * @author eguchi
 */
public class Model{

    private String height = "";
    private String weight = "";
    private String bmi = "";


    public String getWeight(){
        return weight;
    }

    public String getHeight(){
        return height;
    }

    public String getBmi(){
        return bmi;
    }

    public void setWeight( String weight ){
        this.weight = weight;
        updateBMI();
    }


    public void setHeight( String height ){
        this.height = height;
        updateBMI();
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

}
