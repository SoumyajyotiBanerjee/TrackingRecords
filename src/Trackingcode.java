
public class Trackingcode {
	String statusCode;
	int transferCode;
	Range range;
	boolean invalid;
	
	Trackingcode()
	{
		invalid = false;
	}
	
	TrackingCode(int lo, int hi, String statusCode, int transferCode)
	{
		invalid = false;
		range = new Range(lo, hi);
		this.statusCode = statusCode;
		this.transferCode = transferCode;
	}
}
