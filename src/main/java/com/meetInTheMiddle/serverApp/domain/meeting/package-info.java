/**
 * @author Felix
 */
@XmlSchema(elementFormDefault = XmlNsForm.QUALIFIED, namespace = MEETINGS_NS, xmlns = @XmlNs(prefix = "", namespaceURI = MEETINGS_NS))
@XmlAccessorType(FIELD)
package com.meetInTheMiddle.serverApp.domain.meeting;

import static com.meetInTheMiddle.serverApp.util.Constants.MEETINGS_NS;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;