import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proyecto } from '../model/proyecto';

@Injectable({
  providedIn: 'root'
})
export class ProyectoServService {

  proURL= 'https://backend-marce.herokuapp.com/pro/'
  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Proyecto[]>{
    return this.httpClient.get<Proyecto[]>(this.proURL + 'lista');

  }
  public detail(id:number): Observable<Proyecto>{
    return this.httpClient.get<Proyecto>(this.proURL + `detail/${id}`);
  }

  public save(pro:Proyecto): Observable<any>{
    return this.httpClient.post<any>(this.proURL + 'create', pro);
  }

  public update(id: number, pro:Proyecto): Observable<any>{
    return this.httpClient.put<any>(this.proURL + `update/${id}`,pro);
  }

  public delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.proURL+ `delete/${id}`);
  }
}
