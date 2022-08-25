import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Proyecto } from 'src/app/model/proyecto';
import { ProyectoServService } from 'src/app/service/proyecto-serv.service';

@Component({
  selector: 'app-new-proyecto',
  templateUrl: './new-proyecto.component.html',
  styleUrls: ['./new-proyecto.component.css']
})
export class NewProyectoComponent implements OnInit {

  nombrePro: string = '';
  descripcionPro: string = '';
  imgPro: string = '';
  constructor(private proyectoServ:ProyectoServService, private router: Router) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    const pro= new Proyecto(this.nombrePro, this.descripcionPro, this.imgPro);
    this.proyectoServ.save(pro).subscribe(data => {
      alert("Proyecto añadido");
      this.router.navigate(['']);
    }, err => {
      alert("Algo ha fallado!");
      this.router.navigate(['']);
    }
    )
  }
}
