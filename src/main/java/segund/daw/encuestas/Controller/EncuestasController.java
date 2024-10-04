package segund.daw.encuestas.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import segund.daw.encuestas.Entity.Encuesta;
import segund.daw.encuestas.Repository.EncuestaRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class EncuestasController {

    private EncuestaRepository encuestaRepository;



    public EncuestasController(EncuestaRepository repository){
        this.encuestaRepository = repository;
    }



    @GetMapping("/encuesta")
    public String newEncuesta(Model model) {
        Encuesta encuesta = new Encuesta();
        model.addAttribute("encuesta", encuesta);
        return "encuesta-add";
    }

    @PostMapping("/encuesta/save")
    public String saveEncuesta(Model model, Encuesta encuesta, BindingResult bindingResult) {
        model.addAttribute("encuesta", encuesta);
        if (bindingResult.hasErrors()) {
            return "encuesta-add";
        }
        encuestaRepository.save(encuesta);
        //return "encuesta-success";
        return"redirect:/admin";
    }

    //----Accede al panel de admin -----------------------------
    @GetMapping("/admin")
    public String cargarEncuestas(Model model){
        List<Encuesta> encuestas = this.encuestaRepository.findAll();

        //Pasamos los datos a la vista
        model.addAttribute("encuestas",encuestas);

        return "admin";
    }
    //----------------------------------------------------------------

    //----- Métodos para editar la encuesta -----------------------------
    @GetMapping("/encuesta/editar/{id}")
    public String editarEncuesta(@PathVariable Long id, Model model){
        //optional para contemplar la posibilidad de que no exista producto con ese id
        // además el optional contiene metodos como el isPresent
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if(encuesta.isPresent()){

            //para pasarle a la vista el objeto
            model.addAttribute("encuesta",encuesta.get());
            return "encuesta-edit";
        }

        //redirigir al listado de productos
        return "redirect:/admin";
    }

    //por post para mandar los datos
    @PostMapping("/encuesta/editar/{id}")
    public String editarEncuestaActualizar(@PathVariable Long id ,Encuesta encuesta)
    {
        encuesta.setId(id);
        encuestaRepository.save(encuesta);
        //redirigir al listado de productos
        return "redirect:/admin";
    }
    //---------------------------------------------------------------


    @GetMapping("/encuesta/seleccionar/{id}")
    public String seleccionarEncuesta(@PathVariable Long id, Model model){
        //optional para contemplar la posibilidad de que no exista producto con ese id
        // además el optional contiene metodos como el isPresent
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if(encuesta.isPresent()){

            //para pasarle a la vista el objeto
            model.addAttribute("encuesta",encuesta.get());

            return "encuesta-sel";
        }

        //redirigir al listado de productos
        return "redirect:/admin";
    }


    @PostMapping("/encuesta/volver/admin")
    public String seleccionarEncuestaVolver(){
        return "redirect:/admin";
    }






    //-------------- Métodos para eliminar la encuesta --------------------
    @GetMapping("/encuesta/eliminar/{id}")
    public String eliminarEncuestaBorrar(@PathVariable Long id , Model model)
    {
        //optional para contemplar la posibilidad de que no exista producto con ese id
        // además el optional contiene metodos como el isPresent
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if(encuesta.isPresent()){
            encuestaRepository.deleteById(id);
            return "redirect:/admin";
        }

        //redirigir al listado de productos
        return "redirect:/admin";
    }



}
