package br.com.sobre.sobremesa.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sobre.sobremesa.model.Sobremesa;
import br.com.sobre.sobremesa.service.FileStorageService;
import br.com.sobre.sobremesa.service.SobremesaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class SobremesaController {
    SobremesaService service;

    @Autowired
    FileStorageService fileStorageService;
    public SobremesaController(SobremesaService service, FileStorageService fileStorageService){
        this.service = service;
        this.fileStorageService = fileStorageService;      

    }

    @GetMapping("/")
    public String getIndex(Model model, HttpServletResponse response, HttpSession session){

        List<Sobremesa> sobremesa = service.findAll();
        model.addAttribute("sobremesa", sobremesa);

        model.addAttribute("sobremesa", sobremesa);

        Cookie cookie = new Cookie("visita", "cookie-value");
        cookie.setMaxAge(60*60*60);
        response.addCookie(cookie);

        return "index";

    }

    @GetMapping("/cadastrar")
    public String doCadastrar(Model model){
        Sobremesa s = new Sobremesa();
        model.addAttribute("sobremesa", s);

        return "cadastrar";
    }

    @PostMapping("/salvar")
    public String doSalvaSobremesa(@ModelAttribute @Valid Sobremesa s, Errors errors,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request){
        if (errors.hasErrors()){
            redirectAttributes.addAttribute("msg", "Cadastro fracassado");
            return "redirect:/";
        }else {
                
            try {
                s.setImage_uri(file.getOriginalFilename());
                service.update(s);
                fileStorageService.save(file);

                redirectAttributes.addFlashAttribute("msgSucesso", "Salvo com sucesso");
                return "redirect:/";
            } catch (Exception e) {
                redirectAttributes.addAttribute("msg", "Cadastro Fracassado!");
                return "cadastrar";
            }
        }
     }

    /*@GetMapping("editar/{id}")
    public String getEditarSobremesa(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes){
        
        Sobremesa sobremesa = service.findById(id);
        model.addAttribute("sobremesa", sobremesa);
            
        redirectAttributes.addAttribute("msg2", "Cadastro atualizado com sucesso");
            return "cadastrar";
    } */

    // @GetMapping("deletar/{id}")
    // public String deletarComputador(@PathVariable Long id, RedirectAttributes redirectAttributes){
    //     Sobremesa s = service.findById(id);
    //     s.setDeleted(new Date(System.currentTimeMillis()));
    //     service.create(s);
    //     redirectAttributes.addAttribute("msg", "Deletado com sucesso");
    //     return "redirect:/";
    // }

    @GetMapping("/admin")
    public String adminPage(Model model){
        List<Sobremesa> lista = new ArrayList<>();
            lista = service.findAll();

        model.addAttribute("computadores", lista);

        return "admin";
        }



    
}
