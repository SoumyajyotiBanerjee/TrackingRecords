import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TrackingRecords {
	
	List<Trackingcode> recordlist = new ArrayList<Trackingcode>();

	public void insertRecord(Trackingcode newRecord)
	{
		int i=0;
		
		for(Trackingcode listrecord : recordlist)
		{
			if(newRecord.range.lo<listrecord.range.lo)
			{
				recordlist.add(i, newRecord);
				break;
			}
			i++;
		}
		
		recordlist.add(newRecord);
	}
	
	public void splitRecordList(Trackingcode part1, Trackingcode part2)
	{
		
		int i=0;	
		for(Trackingcode listrecord : recordlist)
		{
			if(part1.range.lo == listrecord.range.lo && part2.range.hi == listrecord.range.hi)
			{
				recordlist.remove(i);
				recordlist.add(i, part1);
				recordlist.add(i+1, part2);
				break;
			}
			i++;
		}
	}
	
	public void mergeRecordList(Trackingcode part1, Trackingcode part2)
	{
		
	}
	public void displayRecordList()
	{
		for(Trackingcode listrecord : recordlist)
		{
			if(!listrecord.invalid)
			{
				System.out.println(" "+listrecord.range.hi+" - "+listrecord.range.lo+" "+listrecord.statusCode+" "+listrecord.transferCode);
			}
		}
	}
	
	public void insertingIntoRecordList(Trackingcode newRecord)
	{
		for(Trackingcode listrecord : recordlist)
		{
			
		}
	}
	
	public Trackingcode processInput(String recordinformation)
	{
		Trackingcode newRecord = null;
		
		String[] informationaboutrecord = recordinformation.split(" ");
		newRecord.range.lo = Integer.parseInt(informationaboutrecord[0]);
		newRecord.range.lo = Integer.parseInt(informationaboutrecord[1]);
		newRecord.statusCode = informationaboutrecord[2];
		newRecord.transferCode = Integer.parseInt(informationaboutrecord[3]);
		
		return newRecord;
	}
	
	public void readInput()
	{
		Scanner input = new Scanner(System.in);
		
		String nameOfTestCase = null;
		
		while(!(nameOfTestCase= input.nextLine()).equals("END"))
		{
			
			String information = null;
		
			while(!(information=input.nextLine()).equals("0"))
			{
			Trackingcode newRecord = processInput(information);
			insertingIntoRecordList(newRecord);
			}
		
			System.out.println(nameOfTestCase);
			displayRecordList();
			recordlist = new ArrayList<Trackingcode>();
		}
	}
	
}
