import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Skills } from 'src/app/model/skills';
import { SkillsServService } from 'src/app/service/skills-serv.service';

@Component({
  selector: 'app-edit-skill',
  templateUrl: './edit-skill.component.html',
  styleUrls: ['./edit-skill.component.css']
})
export class EditSkillComponent implements OnInit {

  ski: Skills=null;
  constructor(private skillsServ: SkillsServService, private activatedRouter: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.skillsServ.detail(id).subscribe(data => {
      this.ski = data;
    },err =>{
      alert("Error al modificar habilidad");
      this.router.navigate(['']);
    }
    )
  }

  onUpdate(): void {
    const id = this.activatedRouter.snapshot.params['id'];
    this.skillsServ.update(id, this.ski).subscribe(data => {
      alert("Habilidad modificada");
      this.router.navigate(['']);
    }, err => {
      alert("Error al modificiar habilidad");
      this.router.navigate(['']);
    }
    )
  }

}
