package com.kail.robot.bean;

import java.util.ArrayList;

import com.kail.robot.iterface.*;

public class ActionBean {
		private int id;
		private String name;
		private ArrayList<IActionData> actionList;
		
		public ActionBean(int id, String name) {
			super();
			this.id = id;
			this.name = name;

		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public ArrayList<IActionData> getActionList() {
			return actionList;
		}
		public void setActionList(ArrayList<IActionData> actionList) {
			this.actionList = actionList;
		}

		
}
