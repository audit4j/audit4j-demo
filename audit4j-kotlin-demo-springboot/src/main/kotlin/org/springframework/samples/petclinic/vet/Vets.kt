package org.springframework.samples.petclinic.vet

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple domain object representing a list of veterinarians. Mostly here to be used for the 'vets' {@link
 * org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * @author Arjen Poutsma
 */

@XmlRootElement
data class Vets(var vetList: Collection<Vet>? = null)
