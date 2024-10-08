- ¿Qué sucede si intentas borrar una encuesta que no existe? ¿Cómo lo has controlado?
->Que no elimina ningun valor y retorna al formulario admin, esto lo controlamos con el método .isPresent()
  tal y como muestro acontinuación.
  
      if(encuesta.isPresent()){
            encuestaRepository.deleteById(id);
            return "redirect:/admin";
        }

        //redirigir al listado
        return "redirect:/admin";

  
- Si introduces en un texto del tipo <style>body background-color:red</style> en uno de los
campos ¿Qué sucede al ver la encuesta? ¿el navegador ejecuta ese código o no? ¿porqué?

->No sucede nada porque el motor de plantillas, en este caso Thymeleaf esta preparado para evitar estas situaciones,
ya que son consideradas como ataques de inyección de código malicioso denominados ataques XSS.

¿cómo podrías hacer que se ejecute ese código o que se deje de ejecutar?

->Se podría usar la propiedad th:utext en lugar de th:text para mostrar contenido sin controlarlo.

- ¿Qué has tenido que modificar en el repositorio para filtrar por motivo de búsqueda? ¿has
tenido que escribir el código específico o como lo has realizado?

->Dentro de la interfaz del repositorio he tenido que añadir otro findby para buscar por nivel de satisfación
de esta manera :    List<Encuesta> findByNivelSatisfacion(String nivelSatisfacion);
