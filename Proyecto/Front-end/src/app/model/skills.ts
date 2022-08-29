export class Skills {
    id?: number;
    nombre: string;
    porcentaje: number;
    urlLogo: string;

    constructor(nombre: string, porcentaje: number, urlLogo: string) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.urlLogo = urlLogo;
    }
}
