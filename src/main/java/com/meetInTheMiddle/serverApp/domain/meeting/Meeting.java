package com.meetInTheMiddle.serverApp.domain.meeting;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="meeting")
public class Meeting {
	public Meeting(Long id, Long pers1_fk, Long pers2_fk, Date uhrzeit,
			Long lokalitaet_fk, Long ort_fk, int bewertung,
			Long verkehrsmittel_pers1_fk, String kommentar,
			Long verkehrsmittel_pers2_fk, String aIdSender, String aIdEmpfaenger, String message) {
		super();
		this.id = id;
		this.pers1_fk = pers1_fk;
		this.pers2_fk = pers2_fk;
		this.uhrzeit = uhrzeit;
		this.lokalitaet_fk = lokalitaet_fk;
		this.ort_fk = ort_fk;
		this.bewertung = bewertung;
		this.verkehrsmittel_pers1_fk = verkehrsmittel_pers1_fk;
		this.kommentar = kommentar;
		this.verkehrsmittel_pers2_fk = verkehrsmittel_pers2_fk;
		this.aIdSender = aIdSender;
		this.aIdEmpfaenger = aIdEmpfaenger;
		this.message = message;
	}
	public Meeting(Long pers1_fk, Long pers2_fk, Date uhrzeit,
			Long lokalitaet_fk, Long ort_fk, int bewertung,
			Long verkehrsmittel_pers1_fk, String kommentar,
			Long verkehrsmittel_pers2_fk) {
		super();
		this.pers1_fk = pers1_fk;
		this.pers2_fk = pers2_fk;
		this.uhrzeit = uhrzeit;
		this.lokalitaet_fk = lokalitaet_fk;
		this.ort_fk = ort_fk;
		this.bewertung = bewertung;
		this.verkehrsmittel_pers1_fk = verkehrsmittel_pers1_fk;
		this.kommentar = kommentar;
		this.verkehrsmittel_pers2_fk = verkehrsmittel_pers2_fk;
	}
	public Meeting(){
	}

	@XmlElement
	private Long id;
	@XmlElement(required=true)
	private Long pers1_fk;
	@XmlElement(required=true)
	private Long pers2_fk;
	@XmlElement(required=true)
	private Date uhrzeit;
	@XmlElement
	private Long lokalitaet_fk;
	@XmlElement(required = true)
	private Long ort_fk;
	@XmlElement
	private int bewertung;
	@XmlElement
	private Long verkehrsmittel_pers1_fk;
	@XmlElement
	private String kommentar;
	@XmlElement
	private Long verkehrsmittel_pers2_fk;
	@XmlElement
	private String aIdSender;
	@XmlElement
	private String aIdEmpfaenger;
	@XmlElement(required = false)
	private String message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPers1_fk() {
		return pers1_fk;
	}
	public void setPers1_fk(Long pers1_fk) {
		this.pers1_fk = pers1_fk;
	}
	public Long getPers2_fk() {
		return pers2_fk;
	}
	public void setPers2_fk(Long pers2_fk) {
		this.pers2_fk = pers2_fk;
	}
	public Date getUhrzeit() {
		return uhrzeit;
	}
	public void setUhrzeit(Date uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	public Long getLokalitaet_fk() {
		return lokalitaet_fk;
	}
	public void setLokalitaet_fk(Long lokalitaet_fk) {
		this.lokalitaet_fk = lokalitaet_fk;
	}
	public Long getOrt_fk() {
		return ort_fk;
	}
	public void setOrt_fk(Long ort_fk) {
		this.ort_fk = ort_fk;
	}
	public int getBewertung() {
		return bewertung;
	}
	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}
	public Long getVerkehrsmittel_pers1_fk() {
		return verkehrsmittel_pers1_fk;
	}
	public void setVerkehrsmittel_pers1_fk(Long verkehrsmittel_pers1_fk) {
		this.verkehrsmittel_pers1_fk = verkehrsmittel_pers1_fk;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public Long getVerkehrsmittel_pers2_fk() {
		return verkehrsmittel_pers2_fk;
	}
	public void setVerkehrsmittel_pers2_fk(Long verkehrsmittel_pers2_fk) {
		this.verkehrsmittel_pers2_fk = verkehrsmittel_pers2_fk;
	}
	public String getaIdSender() {
		return aIdSender;
	}
	public void setaIdSender(String aIdSender) {
		this.aIdSender = aIdSender;
	}
	public String getaIdEmpfaenger() {
		return aIdEmpfaenger;
	}
	public void setaIdEmpfaenger(String aIdEmpfaenger) {
		this.aIdEmpfaenger = aIdEmpfaenger;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Meeting [id=" + id + ", pers1_fk=" + pers1_fk + ", pers2_fk="
				+ pers2_fk + ", uhrzeit=" + uhrzeit + ", lokalitaet_fk="
				+ lokalitaet_fk + ", ort_fk=" + ort_fk + ", bewertung="
				+ bewertung + ", verkehrsmittel_pers1_fk="
				+ verkehrsmittel_pers1_fk + ", kommentar=" + kommentar
				+ ", verkehrsmittel_pers2_fk=" + verkehrsmittel_pers2_fk
				+ ", aIdSender=" + aIdSender + ", aIdEmpfaenger="
				+ aIdEmpfaenger + ", message=" + message + "]";
	}
	
}
