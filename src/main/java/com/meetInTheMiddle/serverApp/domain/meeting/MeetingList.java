package com.meetInTheMiddle.serverApp.domain.meeting;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meetings")
public class MeetingList {

	@XmlElementRef
	private List<Meeting> list;

	public List<Meeting> getList() {
		return list;
	}

	public void setList(List<Meeting> meetings) {
		this.list = meetings;
	}
}
