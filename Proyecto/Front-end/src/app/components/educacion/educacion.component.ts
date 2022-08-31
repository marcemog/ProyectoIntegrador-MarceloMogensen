import { Component, OnInit } from '@angular/core';
import { Educacion } from 'src/app/model/educacion';
import { EducacionServService } from 'src/app/service/educacion-serv.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-educacion',
  templateUrl: './educacion.component.html',
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent implements OnInit {
  edu: Educacion[]=[];

  constructor(private educacionServ: EducacionServService, private tokenService: TokenService) { }

  isLogged = false;
  ngOnInit(): void {
    this.cargarEducacion();
    if(this.tokenService.getToken()){
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargarEducacion(): void {
    this.educacionServ.lista().subscribe(data=>{this.edu=data});
  }

  delete(id?: number){
    if(id!==undefined){
      this.educacionServ.delete(id).subscribe(data => {
        this.cargarEducacion()}, err => {
          alert("No se ha podido eliminar la educación")})
    }
    }
}
