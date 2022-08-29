import { Component, OnInit } from '@angular/core';
import { Skills } from 'src/app/model/skills';
import { SkillsServService } from 'src/app/service/skills-serv.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  ski : Skills[]=[];
  constructor(private skillsServ: SkillsServService, private tokenService: TokenService) { }

  isLogged = false;
  ngOnInit(): void {
    this.cargarSkill();
    if(this.tokenService.getToken()){
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargarSkill(): void {
    this.skillsServ.lista().subscribe(data => {this.ski = data});
  }

  delete(id?: number){
    if(id!==undefined){
      this.skillsServ.delete(id).subscribe(data => {
        this.cargarSkill()}, err => {
          alert("No se ha podido eliminar la habilidad")})
    }
    }
}
