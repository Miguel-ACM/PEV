package vista;

import javax.swing.AbstractSpinnerModel;

public class PreciseSpinnerModel extends AbstractSpinnerModel {

    private Double value;

    public PreciseSpinnerModel(Double value){
         this.value=value;
    }

    @Override
    public Object getNextValue() {
        value+=0.00000001;
        return value+"";//return as string to avoid round
    }

    @Override
    public Object getPreviousValue() {
        value-=0.00000001;
        return value+"";//return as string to avoid round
    }

    @Override
    public Object getValue() {
        return value+"";//return as string to avoid round
    }

    @Override
    public void setValue(Object object) {
        try{
           value = Double.parseDouble(object.toString());
        } catch(Exception e){
           e.printStackTrace();
        }
    }

}