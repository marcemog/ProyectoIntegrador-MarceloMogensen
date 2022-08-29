export class persona {
    id?: number;
    nombre: string;
    apellido: string;
    img: string;
    formacion: string;
    descripcion: string;

    constructor(nombre: string, apellido: string, img: string, formacion: string, descripcion: string) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.img = img;
        this.formacion = formacion;
        this.descripcion = descripcion;
    }
}