package com.kail.robot.bean;
import com.kail.robot.iterface.*;

public class KeyBoardBean implements IActionData {
		private final String name="keyboard";
		private int keyValue;
		private long time;
		public KeyBoardBean(int keyValue, long time) {
			super();
			this.keyValue = keyValue;
			this.time = time;
		}
		public KeyBoardBean(){
			
		}
		public int getKeyValue() {
			return keyValue;
		}
		public void setKeyValue(int keyValue) {
			this.keyValue = keyValue;
		}
		public long getTime() {
			return time;
		}
		public void setTime(long time) {
			this.time = time;
		}
		public String getName() {
			return name;
		}
		
}
