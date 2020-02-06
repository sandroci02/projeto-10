
export interface Paginador {
    total: number;
	totalPages: number;
	paginas: number[];
	elementos: number;
	pagina : number;
	proximo : number;
	anterior: number;
	asc: Boolean;
	order: String;
	resumo: String;
	semRegistro: Boolean;
}
