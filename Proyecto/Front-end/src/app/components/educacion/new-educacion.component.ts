import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Educacion } from 'src/app/model/educacion';
import { EducacionServService } from 'src/app/service/educacion-serv.service';

@Component({
  selector: 'app-new-educacion',
  templateUrl: './new-educacion.component.html',
  styleUrls: ['./new-educacion.component.css']
})
export class NewEducacionComponent implements OnInit {
  nombreEdu: string = '';
  descripcionEdu: string = '';

  constructor(private educacionServ: EducacionServService, private router: Router) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    const edu= new Educacion(this.nombreEdu, this.descripcionEdu);
    this.educacionServ.save(edu).subscribe(data => {
      alert("Educación añadida");
      this.router.navigate(['']);
    }, err => {
      alert("Algo ha fallado!");
      this.router.navigate(['']);
    }
    )
  }

}
