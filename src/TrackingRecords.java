import java.util.ArrayList;
import java.util.List;


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
				System.out.println(" "+listrecord.range.hi+" - "+listrecord.range.lo+" "+listrecord.statusCode+" "+listrecord.treansferCode);
			}
		}
	}
	
	public void insertingIntoRecordList(Record record)
	{
		
		for(Trackingcode listrecord : recordlist)
		{
			Relation relation = listrecord.range.classify(record.range);
			switch(relation)
			{
				case Relation.SAME:
					listrecord.transferCode = record.transferCode;
					listrecord.statusCode = record.statusCode;
					break;
					
				case Relation.SUPERSET:
					
					Record previousRecord = new Record(listrecord.range.lo, record.range.lo - 1, listrecord.statusCode, listrecord.transferCode);
					Record nextRecord = new Record(record.range.hi + 1, listrecord.range.hi, listrecord.statusCode, listrecord.transferCode);
					insertRecord(previousRecord);
					insertRecord(nextRecord);
					insertRecord(record);
					deleteRecord(currentRecord);
					break;
							
				case Relation.LESSDISJOINT:
					insertRecord(record);
					break;
					
				case Relation.MOREDISJOINT:
					if(!recordlist.hasNext())
						insertRecord(record);
					break;
				
				case Relation.LESSOVERLAP:
					Record previousRecord = new Record(listrecord.range.lo, record.range.lo - 1, listrecord.statusCode, listrecord.transferCode);
					insertRecord(previousRecord);
					insertRecord(record);
					deleteRecord(currentRecord);
					break;
				
				case Relation.MOREOVERLAP:
					Record nextRecord = new Record(record.range.hi + 1, listrecord.range.hi, listrecord.statusCode, listrecord.transferCode);
					deleteRecord(currentRecord);
					insertRecord(record);
					insertRecord(nextRecord);
					break;
				
			}
		}
			
	}
}
