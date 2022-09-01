import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Skills } from '../model/skills';

@Injectable({
  providedIn: 'root'
})
export class SkillsServService {

  skiURL= 'https://backend-marce.herokuapp.com/skills/'
  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Skills[]>{
    return this.httpClient.get<Skills[]>(this.skiURL + 'lista');

  }
  public detail(id:number): Observable<Skills>{
    return this.httpClient.get<Skills>(this.skiURL + `detail/${id}`);
  }

  public save(pro:Skills): Observable<any>{
    return this.httpClient.post<any>(this.skiURL + 'create', pro);
  }

  public update(id: number, pro:Skills): Observable<any>{
    return this.httpClient.put<any>(this.skiURL + `update/${id}`,pro);
  }

  public delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.skiURL+ `delete/${id}`);
  }
}
