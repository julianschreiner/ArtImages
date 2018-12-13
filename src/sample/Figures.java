package sample;

public class Figures {
    private Figure figure;


    public void init(int type){
        //TODO switch case which calls different figurre methods (dreieck, quadrat etc)

        switch (type){
            case 1:
                triangle();
                break;
            default:
                // THROW ERROR
                break;
        }
    };



    public void triangle() {
        figure.set();

    }
    public void mandelbrot(){}


}
