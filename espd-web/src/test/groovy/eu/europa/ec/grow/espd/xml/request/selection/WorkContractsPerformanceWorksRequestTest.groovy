/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class WorkContractsPerformanceWorksRequestTest extends AbstractSelectionCriteriaFixture {

    def "14. should contain the 'For works contracts: performance of works of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = generateRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.WORK_CONTRACTS_PERFORMANCE_OF_WORKS)

        then: "CriterionID element"
        checkCriterionId(request, idx, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.SELECTION.TECHNICAL_PROFESSIONAL_ABILITY.REFERENCES.WORKS_PERFORMANCE")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For works contracts: performance of works of the specified type"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For public works contracts only: During the reference period, the economic operator has performed the following works of the specified type. Contracting authorities may require up to five years and allow experience dating from more than five years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "check description amount date recipients"
        checkDescriptionAmountDateRecipientsGroup1(request.Criterion[idx].RequirementGroup[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}