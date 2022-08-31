package com.portfolio.marce.Controller;

import com.portfolio.marce.Dto.dtoProyecto;
import com.portfolio.marce.Entity.Proyecto;
import com.portfolio.marce.Security.Controller.Mensaje;
import com.portfolio.marce.Service.ProyectoServ;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pro")
@CrossOrigin(origins = "http://localhost:4200")
public class CProyecto {
    
    @Autowired
    ProyectoServ proyectoServ;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto pro = proyectoServ.getOne(id).get();
        return new ResponseEntity(pro, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtopro) {
        if (StringUtils.isBlank(dtopro.getNombrePro())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (proyectoServ.existsByNombrePro(dtopro.getNombrePro())) {
            return new ResponseEntity(new Mensaje("Proyecto ya existente"), HttpStatus.BAD_REQUEST);
        }

        Proyecto pro = new Proyecto(dtopro.getNombrePro(), dtopro.getDescripcionPro(),dtopro.getImgPro());
        proyectoServ.save(pro);

        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtopro) {
        //validacion existencia de ID
        if (!proyectoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.NOT_FOUND);
        }
        //comparacion nombres de proyectos
        if (proyectoServ.existsByNombrePro(dtopro.getNombrePro()) && proyectoServ.getByNombrePro(dtopro.getNombrePro()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Proyecto ya existente"), HttpStatus.BAD_REQUEST);
        }
        //validacion de campo no vacio
        if (StringUtils.isBlank(dtopro.getNombrePro())) {
            return new ResponseEntity(new Mensaje("El nombre es olbigatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyecto pro = proyectoServ.getOne(id).get();
        pro.setNombrePro(dtopro.getNombrePro());
        pro.setDescripcionPro(dtopro.getDescripcionPro());
        pro.setImgPro(dtopro.getImgPro());
        proyectoServ.save(pro);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);

    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //validacion existencia de ID
        if (!proyectoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.NOT_FOUND);
        }

        proyectoServ.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);

    }
    
}
