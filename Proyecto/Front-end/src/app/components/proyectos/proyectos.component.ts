import { Component, OnInit } from '@angular/core';
import { Proyecto } from 'src/app/model/proyecto';
import { ProyectoServService } from 'src/app/service/proyecto-serv.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-proyectos',
  templateUrl: './proyectos.component.html',
  styleUrls: ['./proyectos.component.css']
})
export class ProyectosComponent implements OnInit {
  
  pro: Proyecto[]=[];
  constructor(private proyectoServ: ProyectoServService, private tokenService: TokenService) { }
  
  isLogged = false;
  ngOnInit(): void {
    this.cargarProyecto();
    if(this.tokenService.getToken()){
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargarProyecto(): void {
    this.proyectoServ.lista().subscribe(data => {this.pro = data});
  }

  delete(id?: number){
    if(id!==undefined){
      this.proyectoServ.delete(id).subscribe(data => {
        this.cargarProyecto()}, err => {
          alert("No se ha podido eliminar el proyecto")})
    }
    }
}
