package Login;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Challenge {

	public Challenge(int passage2, HashMap<String, Float> participants2, int i, String text) {
		passage = passage2;
		participantsAndScores = participants2;
		 Date currentDatetime = new Date(System.currentTimeMillis());  
		 createTime = new Timestamp(currentDatetime.getTime());
		 key = i;
		 this.text = text;
	}

	public Challenge(int passage2, HashMap<String, Float> map,
			int challengeKey, String text2, Timestamp createTime2) {
		passage = passage2;
		participantsAndScores = map;
		 Date currentDatetime = new Date(System.currentTimeMillis());  
		 createTime = new Timestamp(currentDatetime.getTime());
		 key = challengeKey;
		 this.text = text2;
		 createTime = createTime2;
	}

	public String text;
	
	public HashMap<String, Float> participantsAndScores;
	
	public Timestamp createTime;
	
	public int passage;
	 public int key; 
	
}
