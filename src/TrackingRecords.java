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
	
	public void deleteRecord(Trackingcode record)
	{
		int i=0;
		
		for(Trackingcode listrecord : recordlist)
		{
			if(record == listrecord)
			{
				recordlist.remove(i);
				break;
			}
			i++;
		}
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
	

	public void insertingIntoRecordList(Trackingcode record)

	{
		
		for(Trackingcode listrecord : recordlist)
		{
			String decision = listrecord.range.classify(record.range);
			switch(decision)
			{
				case "SAME":
					listrecord.transferCode = record.transferCode;
					listrecord.statusCode = record.statusCode;
					break;
					
				case "SUPERSET":
					
					Trackingcode previousRecord = new Trackingcode(listrecord.range.lo, record.range.lo - 1, listrecord.statusCode, listrecord.transferCode);
					Trackingcode nextRecord = new Trackingcode(record.range.hi + 1, listrecord.range.hi, listrecord.statusCode, listrecord.transferCode);
					insertRecord(previousRecord);
					insertRecord(nextRecord);
					insertRecord(record);
					deleteRecord(listrecord);
					break;
							
				case "LESSDISJOINT":
					insertRecord(record);
					break;
					
				case "MOREDISJOINT":
					if(!recordlist.hasNext())
						insertRecord(record);
					break;
				
				case "LESSOVERLAP":
					Record previousRecord = new Record(listrecord.range.lo, record.range.lo - 1, listrecord.statusCode, listrecord.transferCode);
					insertRecord(previousRecord);
					insertRecord(record);
					deleteRecord(listrecord);
					break;
				
				case "MOREOVERLAP":
					Record nextRecord = new Record(record.range.hi + 1, listrecord.range.hi, listrecord.statusCode, listrecord.transferCode);
					deleteRecord(listrecord);
					insertRecord(record);
					insertRecord(nextRecord);
					break;
				
			}
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
