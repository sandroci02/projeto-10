import { Tipo } from './tipo.model';
import { Referencia } from '../component/referencia/referencia.component';

export interface Conta {
    id: number;
    descricao: String;
    version: number;
    tipo: Tipo;
    referencia: Referencia;
    valor: number;
    padrao: Boolean;
}
