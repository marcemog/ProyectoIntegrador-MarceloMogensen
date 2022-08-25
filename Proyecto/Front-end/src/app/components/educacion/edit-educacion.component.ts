import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Educacion } from 'src/app/model/educacion';
import { EducacionServService } from 'src/app/service/educacion-serv.service';

@Component({
  selector: 'app-edit-educacion',
  templateUrl: './edit-educacion.component.html',
  styleUrls: ['./edit-educacion.component.css']
})
export class EditEducacionComponent implements OnInit {
  edu: Educacion= null
  
  constructor(private educacionServ: EducacionServService, private activatedRouter: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.educacionServ.detail(id).subscribe(data => {
      this.edu = data;
    },err =>{
      alert("Error al modificiar educación");
      this.router.navigate(['']);
    }
    )
  }

  onUpdate(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.educacionServ.update(id, this.edu).subscribe(data => {
      alert("Educación modificada");
      this.router.navigate(['']);
    }, err => {
      alert("Error al modificiar educación");
      this.router.navigate(['']);
    }
    )
  }

}
