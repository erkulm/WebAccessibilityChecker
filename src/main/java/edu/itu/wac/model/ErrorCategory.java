package edu.itu.wac.model;
public class ErrorCategory {
	protected String errorId;
	protected String header;
	protected String errorDesc;
	public String header2;
	protected String[] codes = {"1.1.1","1.2.1","1.2.2","1.2.3","1.2.4","1.2.5","1.2.6","1.2.7","1.2.8","1.2.9",
			"1.3.1","1.3.2","1.3.3","1.4.1","1.4.2","1.4.3","1.4.4","1.4.5","1.4.6","1.4.7","1.4.8","1.4.9","2.1.1",
			"2.1.2","2.1.3","2.2.1","2.2.2","2.2.3","2.2.4","2.2.5","2.3.1","2.3.2","2.4.1","2.4.2","2.4.3","2.4.4",
			"2.4.5","2.4.6","2.4.7","2.4.8","2.4.9","2.4.10","3.1.1","3.1.2","3.1.3","3.1.4","3.1.5","3.1.6","3.2.1",
			"3.2.2","3.2.3","3.2.4","3.2.5","3.3.1","3.3.2","3.3.3","3.3.4","3.3.5","3.3.6","4.1.1","4.1.2" };
	
	protected String[] desc = {"Non-text Content","Audio-only and Video-only (Prerecorded)","Captions (Prerecorded)",
			"Audio Description or Media Alternative (Prerecorded)","Captions (Live)","Audio Description (Prerecorded)",
			"Sign Language (Prerecorded)","Extended Audio Description (Prerecorded)","Media Alternative (Prerecorded)",
			"Audio-only (Live)","Info and Relationships","Meaningful Sequence","Sensory Characteristics","Use of Color",
			"Audio Control","Contrast (Minimum)","Resize text","Images of Text","Contrast (Enhanced)","Low or No Background Audio",
			"Visual Presentation","Images of Text (No Exception)","Keyboard","No Keyboard Trap","Keyboard (No Exception)",
			"Timing Adjustable","Pause, Stop, Hide","No Timing","Interruptions","Re-authenticating","Three Flashes or Below Threshold",
			"Three Flashes","Bypass Blocks","Page Titled","Focus Order","Link Purpose (In Context)","Multiple Ways","Headings and Labels",
			"Focus Visible","Location","Link Purpose (Link Only)","Section Headings","Language of Page","Language of Parts", "Unusual Words",
			"Abbreviations","Reading Level","Pronunciation","On Focus","On Input","Consistent Navigation","Consistent Identification",
			"Change on Request","Error Identification","Labels or Instructions","Error Suggestion","Error Prevention (Legal, Financial, Data)",
			"Help","Error Prevention (All)","Parsing","Name, Role, Value"	
	};
	
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getHeader() {
		return header;
	}
	public String getHeader2() {
		return header2;
	}
	public void setHeader(String header) {
		
		String num = header.substring(32,37).replace("_",".");  
		int i;
		String newHeader = "No Topic Found";
		for (i = 0; i < this.codes.length; i++) {
			  if(num.equals(codes[i])) {
				  newHeader = this.desc[i];
				  break;
			  }
		}
		this.header2 = header;
		this.header = newHeader;
	}
	
	
	
}



