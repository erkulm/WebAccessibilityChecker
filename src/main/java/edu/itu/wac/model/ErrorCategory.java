package edu.itu.wac.model;

public class ErrorCategory {
	protected String errorId;
	protected String header;
	protected String errorDesc;
	public String link;
	public String detail;
	public String header2;
	public String guideLines;
	protected String[] codes = { "1.1.1", "1.2.1", "1.2.2", "1.2.3", "1.2.4", "1.2.5", "1.2.6", "1.2.7", "1.2.8",
			"1.2.9", "1.3.1Center", "1.3.1Big", "1.3.1AlignAttr", "1.3.2", "1.3.3", "1.4.1", "1.4.2", "1.4.3", "1.4.4",
			"1.4.5", "1.4.6", "1.4.7", "1.4.8", "1.4.9", "2.1.1", "2.1.2", "2.1.3", "2.2.1", "2.2.2", "2.2.3", "2.2.4",
			"2.2.5", "2.3.1", "2.3.2", "2.4.1", "2.4.2", "2.4.3", "2.4.4", "2.4.5", "2.4.6", "2.4.7", "2.4.8", "2.4.9",
			"2.4.10", "3.1.1", "3.1.2", "3.1.3", "3.1.4", "3.1.5", "3.1.6", "3.2.1", "3.2.2", "3.2.3", "3.2.4", "3.2.5",
			"3.3.1", "3.3.2", "3.3.3", "3.3.4", "3.3.5", "3.3.6", "4.1.1", "4.1.2" };

	protected String[] desc = { "Non-text Content", "Audio-only and Video-only (Prerecorded)", "Captions (Prerecorded)",
			"Audio Description or Media Alternative (Prerecorded)", "Captions (Live)",
			"Audio Description (Prerecorded)", "Sign Language (Prerecorded)",
			"Extended Audio Description (Prerecorded)", "Media Alternative (Prerecorded)", "Audio-only (Live)",
			"Info and Relationships Center", "Info and Relationships Big", "Info and Relationships Align Attribute",
			"Meaningful Sequence", "Sensory Characteristics", "Use of Color", "Audio Control", "Contrast (Minimum)",
			"Resize text", "Images of Text", "Contrast (Enhanced)", "Low or No Background Audio", "Visual Presentation",
			"Images of Text (No Exception)", "Keyboard", "No Keyboard Trap", "Keyboard (No Exception)",
			"Timing Adjustable", "Pause, Stop, Hide", "No Timing", "Interruptions", "Re-authenticating",
			"Three Flashes or Below Threshold", "Three Flashes", "Bypass Blocks", "Page Titled", "Focus Order",
			"Link Purpose (In Context)", "Multiple Ways", "Headings and Labels", "Focus Visible", "Location",
			"Link Purpose (Link Only)", "Section Headings", "Language of Page", "Language of Parts", "Unusual Words",
			"Abbreviations", "Reading Level", "Pronunciation", "On Focus", "On Input", "Consistent Navigation",
			"Consistent Identification", "Change on Request", "Error Identification", "Labels or Instructions",
			"Error Suggestion", "Error Prevention (Legal, Financial, Data)", "Help", "Error Prevention (All)",
			"Parsing", "Name, Role, Value" };

	protected String[] topDesc = {
			"Guideline 1.1 Text Alternatives: Provide text alternatives for any non-text content so that it can be changed into other forms people need, such as large print, braille, speech, symbols or simpler language.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Provide alternatives for time-based media.",
			"Guideline 1.2 Time-based Media: Create content that can be presented in different ways (for example simpler layout) without losing information or structure.",
			"Guideline 1.3 Adaptable: Create content that can be presented in different ways (for example simpler layout) without losing information or structure.",
			"Guideline 1.3 Adaptable: Create content that can be presented in different ways (for example simpler layout) without losing information or structure.",
			"Guideline 1.3 Adaptable: Create content that can be presented in different ways (for example simpler layout) without losing information or structure.",
			"Guideline 1.3 Adaptable: Time-based Media: Create content that can be presented in different ways (for example simpler layout) without losing information or structure.",
			"Guideline 1.3 Adaptable: Create content that can be presented in different ways (for example simpler layout) without losing information or structure.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 1.4 Distinguishable: Make it easier for users to see and hear content including separating foreground from background.",
			"Guideline 2.1 Keyboard Accessible: Make all functionality available from a keyboard.",
			"Guideline 2.1 Keyboard Accessible: Make all functionality available from a keyboard.",
			"Guideline 2.1 Keyboard Accessible: Make all functionality available from a keyboard.",
			"Guideline 2.2 Enough Time: Provide users enough time to read and use content.",
			"Guideline 2.2 Enough Time: Provide users enough time to read and use content.",
			"Guideline 2.2 Enough Time: Provide users enough time to read and use content.",
			"Guideline 2.2 Enough Time: Provide users enough time to read and use content.",
			"Guideline 2.2 Enough Time: Provide users enough time to read and use content.",
			"Guideline 2.3 Seizures: Do not design content in a way that is known to cause seizures.",
			"Guideline 2.3 Seizures: Do not design content in a way that is known to cause seizures.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 2.4 Navigable: Provide ways to help users navigate, find content, and determine where they are.",
			"Guideline 3.1 Readable: Make text content readable and understandable.",
			"Guideline 3.1 Readable: Make text content readable and understandable.",
			"Guideline 3.1 Readable: Make text content readable and understandable.",
			"Guideline 3.1 Readable: Make text content readable and understandable.",
			"Guideline 3.1 Readable: Make text content readable and understandable.",
			"Guideline 3.1 Readable: Make text content readable and understandable.",
			"Guideline 3.2 Predictable: Make Web pages appear and operate in predictable ways.",
			"Guideline 3.2 Predictable: Make Web pages appear and operate in predictable ways.",
			"Guideline 3.2 Predictable: Make Web pages appear and operate in predictable ways.",
			"Guideline 3.2 Predictable: Make Web pages appear and operate in predictable ways.",
			"Guideline 3.2 Predictable: Make Web pages appear and operate in predictable ways.",
			"Guideline 3.3 Input Assistance: Help users avoid and correct mistakes.",
			"Guideline 3.3 Input Assistance: Help users avoid and correct mistakes.",
			"Guideline 3.3 Input Assistance: Help users avoid and correct mistakes.",
			"Guideline 3.3 Input Assistance: Help users avoid and correct mistakes.",
			"Guideline 3.3 Input Assistance: Help users avoid and correct mistakes.",
			"Guideline 3.3 Input Assistance: Help users avoid and correct mistakes.",
			"Guideline 4.1 Compatible: Maximize compatibility with current and future user agents, including assistive technologies.",
			"Guideline 4.1 Compatible: Maximize compatibility with current and future user agents, including assistive technologies." };

	protected String[] links = { "https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-text-equiv-all",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#audio-only-and-video-only-prerecorded",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-captions",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-audio-desc",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-real-time-captions",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-audio-desc-only",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-sign",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-extended-ad",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-text-doc",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-media-equiv-live-audio-only",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-content-structure-separation-programmatic",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-content-structure-separation-programmatic",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-content-structure-separation-programmatic",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-content-structure-separation-sequence",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-content-structure-separation-understanding",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-without-color",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-dis-audio",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-contrast",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-scale",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-text-presentation",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast7",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-noaudio",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-visual-presentation",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-visual-audio-contrast-text-images",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-keyboard-operation-keyboard-operable",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-keyboard-operation-trapping",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-keyboard-operation-all-funcs",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-time-limits-required-behaviors",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-time-limits-pause",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-time-limits-no-exceptions",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-time-limits-postponed",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-time-limits-server-timeout",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-seizure-does-not-violate",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-seizure-three-times",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-skip",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-title",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-focus-order",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-refs",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-mult-loc",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-descriptive",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-focus-visible",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-location",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-link",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-navigation-mechanisms-headings",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-meaning-doc-lang-id",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-meaning-other-lang-id",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-meaning-idioms",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-meaning-located",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-meaning-supplements",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-meaning-pronunciation",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-consistent-behavior-receive-focus",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-consistent-behavior-unpredictable-change",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-consistent-behavior-consistent-locations",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-consistent-behavior-consistent-functionality",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-consistent-behavior-no-extreme-changes-context",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-minimize-error-identified",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-minimize-error-cues",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-minimize-error-suggestions",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-minimize-error-reversible",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-minimize-error-context-help",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-minimize-error-reversible-all",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-ensure-compat-parses",
			"https://www.w3.org/WAI/WCAG21/quickref/?versions=2.0#qr-ensure-compat-rsv",
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

	public String getLink() {
		return this.link;
	}

	public String getDetail() {
		return this.detail;
	}

	public String getHeader() {
		return header;
	}

	public String getHeader2() {
		return header2;
	}

	public void setHeader(String header) {

		String num = header.substring(32, 37).replace("_", ".");
		int i;
		if (num.equals("1.3.1")) {
			String ext = header.substring(42);
			num = num + ext;
		}
		String newHeader = "No Topic Found";
		String mydesc = "No Topic Found";
		String mylink = "No Link Found";

		for (i = 0; i < this.codes.length; i++) {
			if (num.equals(codes[i])) {
				newHeader = this.desc[i];
				mydesc = this.topDesc[i];
				mylink = this.links[i];
				break;
			}
		}


		this.link = mylink;
		this.detail = mydesc;
		this.header2 = header;
		this.header = newHeader;
	}

}