package common.requests;

public class CountByMetersAboveSeaLevelRequest extends Request{

    private final int meters;

    public CountByMetersAboveSeaLevelRequest(int meters) {
        super("count_by_meters_above_sea_level");
        this.meters = meters;
    }

    public int getMeters(){
        return meters;
    }

}
