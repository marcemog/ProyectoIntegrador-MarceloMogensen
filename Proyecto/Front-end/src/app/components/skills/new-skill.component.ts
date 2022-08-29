import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Skills } from 'src/app/model/skills';
import { SkillsServService } from 'src/app/service/skills-serv.service';

@Component({
  selector: 'app-new-skill',
  templateUrl: './new-skill.component.html',
  styleUrls: ['./new-skill.component.css']
})
export class NewSkillComponent implements OnInit {

    nombre: string = '';
    porcentaje: number;
    urlLogo: string = '';

  constructor(private skillsServ:SkillsServService, private router: Router) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    const pro= new Skills(this.nombre, this.porcentaje, this.urlLogo);
    this.skillsServ.save(pro).subscribe(data => {
      alert("Habilidad añadida");
      this.router.navigate(['']);
    }, err => {
      alert("Algo ha fallado!");
      this.router.navigate(['']);
    }
    )
  }
}
