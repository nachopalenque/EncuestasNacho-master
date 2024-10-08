package segund.daw.encuestas.Controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String saveEncuesta(Model model, @Valid Encuesta encuesta, BindingResult bindingResult) {
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
        model.addAttribute("media",calcularPromedio(encuestas));
        model.addAttribute("MuySatisfecho",calculaParteSatisfacion("Muy satisfecho",encuestas));
        model.addAttribute("Satisfecho",calculaParteSatisfacion("Satisfecho",encuestas));
        model.addAttribute("Neutral",calculaParteSatisfacion("Neutral",encuestas));
        model.addAttribute("Insatisfecho",calculaParteSatisfacion("Insatisfecho",encuestas));
        model.addAttribute("Muyinsatisfecho",calculaParteSatisfacion("Muy insatisfecho",encuestas));

        return "admin";
    }
    //----------------------------------------------------------------

    public Double calcularPromedio(List<Encuesta> encuestas){
        double media=0;

        for ( Encuesta encuesta : encuestas) {
            media = media+encuesta.getEdad();
        }
        media= media / encuestas.size();
        return media;
    }

    public Double calculaParteSatisfacion(String nivelSatisfacion, List<Encuesta> encuestas){

        int numNivelSatisfacion=0;

        for ( Encuesta encuesta : encuestas) {

            if (encuesta.getNivelSatisfacion().equals(nivelSatisfacion)){
                numNivelSatisfacion++;
            }
        }
        return calcularPorcentajeDelTotal(numNivelSatisfacion,encuestas.size());
    }

    public Double calcularPorcentajeDelTotal(Integer parte, Integer total){

    double porcentaje = 0;

        if (total != 0) {
            // Calcular el porcentaje
           porcentaje = ((double)parte / total) * 100;
        }
        return porcentaje;

    }

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

        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if(encuesta.isPresent()){
            encuestaRepository.deleteById(id);
            return "redirect:/admin";
        }

        return "redirect:/admin";
    }

    @GetMapping("/encuesta/filtrar")
    public String recibirString(@RequestParam("nivelSatisfacion") String nivelSatisfacion,Model model) {

        List<Encuesta> encuestas  = (nivelSatisfacion.equals("Todos") || nivelSatisfacion.equals("") ) ? this.encuestaRepository.findAll() : this.encuestaRepository.findByNivelSatisfacion(nivelSatisfacion);

        model.addAttribute("encuestas",encuestas);
        model.addAttribute("media",calcularPromedio(encuestas));
        model.addAttribute("MuySatisfecho",calculaParteSatisfacion("Muy satisfecho",encuestas));
        model.addAttribute("Satisfecho",calculaParteSatisfacion("Satisfecho",encuestas));
        model.addAttribute("Neutral",calculaParteSatisfacion("Neutral",encuestas));
        model.addAttribute("Insatisfecho",calculaParteSatisfacion("Insatisfecho",encuestas));
        model.addAttribute("Muyinsatisfecho",calculaParteSatisfacion("Muy insatisfecho",encuestas));

        return "admin";


    }

}
