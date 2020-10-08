package com.kail.robot.bean;

import com.kail.robot.iterface.IActionData;

public class MouseBean implements IActionData {
		private final String name="mouse";
		private int x;
		private int y;
		private String click;
		private String dbClick;
		private long time;
		
		public MouseBean(int x, int y, String click, String dbClick, long time) {
			super();
			this.x = x;
			this.y = y;
			this.click = click;
			this.dbClick = dbClick;
			this.time = time;
		}
		public MouseBean(){
			
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public String getClick() {
			return click;
		}
		public void setClick(String click) {
			this.click = click;
		}
		public String getDbClick() {
			return dbClick;
		}
		public void setDbClick(String dbClick) {
			this.dbClick = dbClick;
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
