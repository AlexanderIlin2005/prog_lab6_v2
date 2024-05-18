package common.requests;

public class RemoveKeyRequest extends Request{

    private int key;
    public RemoveKeyRequest(int key) {
        super("remove_key");
        this.key = key;
    }

    public int getKey(){
        return key;
    }
}
