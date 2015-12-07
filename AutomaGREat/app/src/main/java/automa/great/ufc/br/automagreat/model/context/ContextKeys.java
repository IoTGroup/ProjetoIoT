package automa.great.ufc.br.automagreat.model.context;

public class ContextKeys {

    public static final String HUE_LIGHT = "control.ambient.light.hue";
    //public static final String CONTROL_AC = "control.ambient.ac.midea"; // IPDi
    public static final String CONTROL_AC = "control.ambient.ac.yang"; // sala de seminários
    // TODO: 05/11/15 verificar qual das chaves acima é usada

    public static final String MOTION_SENSOR = "control.ambient.motion.sensor"; // sensor de presença

    public static String getContextkeyGetCacs() {
        return "context.ambient.cacs";
    }
    
}
