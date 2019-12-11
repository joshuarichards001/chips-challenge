package maze;

/**
 * An object of this class represents a single square of the grid.
 * The type field encodes which type of tile it is.
 *
 * Wall = #
 * Free = '_'
 *
 * Pink Key = P
 * White Key = W
 * Blue Key = B
 * Gold Key = G
 *
 *
 * Pink Door = K
 * White Door = W
 * Blue Door  = L
 * Gold Door = D
 *
 * Info = ?
 * Treasure = $
 * Exit Lock = +
 * Exit = o
 * Chap = C
 */
public class Tile {
    private Tile.Type type;                // encode the general square content/status
    private boolean holdable = false;
    private boolean free = true;
    private boolean isInfo = false;
    private boolean isPad = false;
    private boolean isPadlock = false;
    private String info = "";
    private String pad = "";

    public enum Type {
        FREE,
        TREASURE,
        BOX,
        PINK_KEY,
        WHITE_KEY,
        BLUE_KEY,
        GOLD_KEY,
        WALL,
        PINK_DOOR,
        WHITE_DOOR,
        BLUE_DOOR,
        GOLD_DOOR,
        INFO,
        LAVA,
        ACID,
        WATER,
        EXIT_LOCK,
        EXIT,
        CHAP_LEFT,
        CHAP_RIGHT,
        CHAP_UP,
        CHAP_DOWN,
        CHAP,
        WHITE_KEY_2,
        PINK_KEY_2,
        BLUE_KEY_2,
        GOLD_KEY_2,
        WEIGHT_PAD,
        PAD_DOOR1,
        PAD_DOOR2,
        PAD_DOOR3
    }

    // tile constructor
    Tile(Type type) {
        this.type = type;
        switch (type) {
            case WALL:
            case PINK_DOOR:
            case GOLD_DOOR :
            case WHITE_DOOR:
            case BLUE_DOOR:
            case EXIT_LOCK:
            case LAVA:
            case ACID:
            case WATER:
            case BOX:
                this.free = false;
                break;
            case PAD_DOOR1:
            case PAD_DOOR2:
            case PAD_DOOR3:
                this.free = false;
                this.isPadlock = true;
                break;
            case PINK_KEY:
            case BLUE_KEY:
            case GOLD_KEY:
            case WHITE_KEY:
            case TREASURE:
                this.holdable = true;
                break;
            case INFO: this.isInfo = true;
                break;
            case WEIGHT_PAD:
                this.isPad = true;
                break;
        }
    }


    // Info tile constructor
    public Tile(Type type, String info) {
        if (type.equals(Type.INFO)) {
            this.info = info;
        }

        else if (type.equals(Type.WEIGHT_PAD)) {
            this.pad = pad;
        }
    }

    Tile.Type getType() {
        return type;
    }

    boolean isHoldable() {
        return holdable;
    }

    boolean isPad() {return isPad;}

    boolean isPadlock() {return isPadlock;}

    boolean isFree() {
        return free;
    }

    boolean isInfo() {
        return isInfo;
    }

    void setInfo() {
        isInfo = true;
    }
    void setPad() {isPad = true;}

    public String getInfo() {
        return info;
    }
    public String getPad() {
        return pad;
    }


    char toChar() {
        switch (type) {
            case WALL : return '#';
            case FREE: return '_';
            case PINK_KEY: return 'P';
            case BLUE_KEY: return 'B';
            case GOLD_KEY: return 'G';
            case WHITE_KEY: return 'W';
            case PINK_DOOR: return 'K';
            case GOLD_DOOR: return 'D';
            case BLUE_DOOR: return 'L';
            case WHITE_DOOR: return 'E';
            case PAD_DOOR1: return '&';
            case PAD_DOOR2: return 'M';
            case PAD_DOOR3: return 'Z';
            case WEIGHT_PAD: return '@';
            case INFO: return '?';
            case TREASURE: return '$';
            case BOX: return 'X';
            case EXIT_LOCK: return '+';
            case EXIT: return 'o';
            case CHAP:
            case CHAP_UP:
            case CHAP_DOWN :
            case CHAP_LEFT :
            case CHAP_RIGHT:
                return 'C';
            default: throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
