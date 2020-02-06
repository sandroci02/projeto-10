import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { PageData } from '../model/PageData.model';
import { ParametroData } from '../model/parametroData.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) { }

  baseUrl = environment.apiPrefix;
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  getList(base: string, parametro: ParametroData) {
    return this.http.post<PageData>(environment.apiPrefix + base + '/list', parametro
    );
  }

  getListAll(base: string, parametro: ParametroData){
    return this.http.post<PageData>(environment.apiPrefix + base + '/listAll', parametro
    );
  } 

  getDetalhe(base: string, parametro: ParametroData) {
    return this.http.post<PageData>(environment.apiPrefix + base + '/find', parametro
    );
  }  

  do(base: string, parametro: ParametroData) {
    return this.http.post<PageData>(environment.apiPrefix + base , parametro
    );
  }  

  save(base: string, parametro: ParametroData) {
    if(parametro.save){
      return this.http.post<PageData>(environment.apiPrefix + base + '/save', parametro
      );
    }else{
      return this.http.post<PageData>(environment.apiPrefix + base + '/update', parametro
      );
    }
  }  

  excluir(base: string, parametro: ParametroData) {
    return this.http.post<PageData>(environment.apiPrefix + base + '/delete', parametro
    );
  }
}
