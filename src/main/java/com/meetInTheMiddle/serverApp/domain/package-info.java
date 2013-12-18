/**
 * 
 */
/**
 * @author Levon
 *
 */
@XmlSchema(elementFormDefault = XmlNsForm.QUALIFIED, namespace = PERSONS_NS, xmlns = @XmlNs(prefix = "", namespaceURI = PERSONS_NS))
@XmlAccessorType(FIELD)
package com.meetInTheMiddle.serverApp.domain;

import static com.meetInTheMiddle.serverApp.util.Constants.PERSONS_NS;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;