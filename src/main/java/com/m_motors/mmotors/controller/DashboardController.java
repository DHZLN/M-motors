
package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.service.DossierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    private final DossierService dossierService;

    public DashboardController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/dossiers")
    public String adminDossiers(Model model) {
        model.addAttribute("dossiers", dossierService.findAll());
        return "admin/dossier";
    }

    @GetMapping("/admin/dossiers/{id}")
    public String detailDossierAdmin(
            @PathVariable Long id,
            Model model) {

        Dossier dossier = dossierService.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dossier non trouvé"));

        model.addAttribute("dossier", dossier);
        model.addAttribute("statuts", StatutDossier.values());

        return "admin/detail-dossier";
    }

    @PostMapping("/admin/dossiers/{id}/statut")
    public String updateStatutDossier(
            @PathVariable Long id,
            @RequestParam("statut") String statut) {

        dossierService.updateStatut(id, statut);

        return "redirect:/admin/dossiers/" + id;
    }

    @PostMapping("/admin/dossiers/{id}/commentaire")
    public String updateCommentaireDossier(
            @PathVariable Long id,
            @RequestParam("internalComment")
            String internalComment) {

        Dossier dossier = dossierService.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dossier non trouvé"));

        dossier.setInternalComment(internalComment);

        dossierService.save(dossier);

        return "redirect:/admin/dossiers/" + id;
    }
}

