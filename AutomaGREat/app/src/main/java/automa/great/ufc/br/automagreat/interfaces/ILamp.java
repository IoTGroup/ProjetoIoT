package automa.great.ufc.br.automagreat.interfaces;

/**
 * Created by romulogmlima on 21/10/15.
 */
public interface ILamp {
    void on( int id);
    void off(int id);
    void setIntensity(String id, String vInts);
}
