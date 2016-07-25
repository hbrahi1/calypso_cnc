/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.calypso.web;

import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.calypso.model.Party;
import com.calypso.service.ClinicService;

@Controller
@SessionAttributes(types = Party.class)
@RequestMapping(value = "/parties")
public class PartyController {

    private final ClinicService clinicService;

    @Autowired
    public PartyController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        Party party = new Party();
        model.addAttribute(party);
        return "parties/partyForm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Party party, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "parties/partyForm";
        } else {
            this.clinicService.saveParty(party);
            status.setComplete();
            return "redirect:/parties/" + party.getId();
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String initFindForm(Model model) {
        model.addAttribute("party", new Party());
        return "parties/findParties";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showParties(Model model) {
        return new ModelAndView("redirect:/parties/list.html", model.asMap());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String processFindForm(Party party, BindingResult result, Model model, HttpSession session) {
        Collection<Party> results = null;

        // find parties by last name
        if (StringUtils.isEmpty(party.getPartyName())) {
            // allow parameterless GET request for /parties to return all records
            results = this.clinicService.findParties();
        } else {
            results = this.clinicService.findPartyByName(party.getPartyName());
        }

        if (results.size() < 1) {
            // no parties found
            result.rejectValue("lastName", "notFound", new Object[] {party.getPartyName()}, "not found");
            return "parties/findParties";
        }

        session.setAttribute("searchLastName", party.getPartyName());

        if (results.size() > 1) {
            // multiple parties found
            model.addAttribute("parties", results);
            return "parties/partiesList";
        } else {
            // 1 party found
            party = results.iterator().next();
            return "redirect:/parties/" + party.getId();
        }
    }

    @RequestMapping(value = "/{partyId}/edit", method = RequestMethod.GET)
    public String initUpdateParty(@PathVariable("partyId") int partyId, Model model) {
        Party party = this.clinicService.findPartyById(partyId);
        model.addAttribute(party);
        return "parties/partyForm";
    }

    @RequestMapping(value = "/{partyId}/edit", method = RequestMethod.POST)
    public String processUpdateParty(@Valid Party party, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "parties/partyForm";
        } else {
            this.clinicService.saveParty(party);
            status.setComplete();
            return "redirect:/parties/{partyId}";
        }
    }

    /**
     * Custom handler for displaying an party.
     *
     * @param partyId
     *            the ID of the party to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/{partyId}")
    public ModelAndView showParty(@PathVariable("partyId") int partyId) {
        ModelAndView mav = new ModelAndView("parties/partyDetails");
        mav.addObject(this.clinicService.findPartyById(partyId));
        return mav;
    }

}
