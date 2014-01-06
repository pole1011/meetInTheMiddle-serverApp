/**
 * @author Felix
 */
@XmlSchema(elementFormDefault = XmlNsForm.QUALIFIED, namespace = PLACES_NS, xmlns = @XmlNs(prefix = "", namespaceURI = PLACES_NS))
@XmlAccessorType(FIELD)
package com.meetInTheMiddle.serverApp.domain.place;

import static com.meetInTheMiddle.serverApp.util.Constants.PLACES_NS;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;