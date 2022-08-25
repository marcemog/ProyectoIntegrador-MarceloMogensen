import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Experiencia } from 'src/app/model/experiencia';
import { ExperienciaServService } from 'src/app/service/experiencia-serv.service';

@Component({
  selector: 'app-new-experiencia',
  templateUrl: './new-experiencia.component.html',
  styleUrls: ['./new-experiencia.component.css']
})
export class NewExperienciaComponent implements OnInit {
  nombreE: string= '';
  descripcionE: string= '';
  constructor(private experienciaServ: ExperienciaServService, private router: Router) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    const expe= new Experiencia(this.nombreE, this.descripcionE);
    this.experienciaServ.save(expe).subscribe(data => {
      alert("Experiencia añadida");
      this.router.navigate(['']);
    }, err => {
      alert("Algo ha fallado!");
      this.router.navigate(['']);
    }
    )
  }

}
