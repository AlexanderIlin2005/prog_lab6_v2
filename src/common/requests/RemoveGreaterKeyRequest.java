package common.requests;

public class RemoveGreaterKeyRequest extends Request{
    private int key;
    public RemoveGreaterKeyRequest(int key) {
        super("remove_greater_key");
        this.key = key;
    }

    public int getKey(){
        return key;
    }
}
