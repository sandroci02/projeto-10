import { Referencia } from '../component/referencia/referencia.component';

export interface Rendimento {
    id: number;
    descricao: String;
    version: number;
    referencia:Referencia;
    valor: number;
}
